package com.example.proyectodivisa

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectodivisa.Database.Moneda
import com.example.proyectodivisa.Database.MonedaDatabase
import com.example.proyectodivisa.Interface.ExchangerateAPI
import com.example.proyectodivisa.Model.Posts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var myJsonTxt : TextView
    private lateinit var button: Button
    private lateinit var uri : Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myJsonTxt = findViewById(R.id.jsonText)
        button = findViewById(R.id.button)
        uri= Uri.parse("content://com.example.proyectodivisa.Content.MyContentProvider/Moneda")

       // contentResolver
        //getPosts()


        button.setOnClickListener{
            val cursor: Cursor? = contentResolver.query(uri, arrayOf("code", "value"), null, null, null)
            cursor?.moveToFirst()
            myJsonTxt.text=""
            while (!cursor!!.isAfterLast)
                myJsonTxt.append("Codigo: " +cursor.getString(0)+" Valor: " + cursor.getString(1) + "\n")
        }

    }



    private fun getPosts(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://v6.exchangerate-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var api : ExchangerateAPI = retrofit.create(ExchangerateAPI::class.java)

        var call : Call<Posts> = api.posts

        call.enqueue(object : Callback<Posts> {
            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                if(!response.isSuccessful) {
                    myJsonTxt.setText("Codigo: " + response.code())
                    return
                }

                var post = response.body()

                val applicationScope = CoroutineScope(SupervisorJob())
                var moneda = Moneda(
                  //  id = 0,
                    code = "",
                    value = 0.0
                )
                for(codes in post!!.conversion_ratesonversions){
                //    moneda!!.code = codes.key
                //    moneda.value = codes.value
                    myJsonTxt.append(moneda.code + "  " + moneda.value.toString() + "\n")
                    MonedaDatabase.getDatabase(applicationContext, applicationScope).MonedaDao().insert(moneda)
                }
            }

            override fun onFailure(call: Call<Posts>, t: Throwable) {
                myJsonTxt.setText(t.message)
            }
        })
    }


}
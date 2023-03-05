package com.example.proyectodivisa

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectodivisa.Database.Moneda
import com.example.proyectodivisa.Database.MonedaDao
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myJsonTxt = findViewById(R.id.jsonText)
        getPosts()
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
                    code = "",
                    value = 0.0
                )
                for(codes in post!!.conversion_ratesonversions){

                    moneda!!.code = codes.key
                    moneda.value = codes.value
                    myJsonTxt.append(moneda.code + "  " + moneda.value.toString())
                    //Log.d("this_app", moneda.code + "  " + moneda.value.toString())
                    MonedaDatabase.getDatabase(applicationContext, applicationScope).MonedaDao().insert(moneda)
                }


            /*
                var content = ""
                content += "result: " + post!!.result + "\n"
                content += "documentation: " + post.documentation + "\n"
                content += "Terms of use: " + post.terms_of_use + "\n"
                content += "Time last update unix: " + post.time_last_update_unix + "\n"
                content += "Time last update utc: " + post.time_last_update_utc + "\n"
                content += "Time next update unix: " + post.time_next_update_unix + "\n"
                content += "Time next update utc: " + post.time_next_update_utc + "\n"
                content += "base code: " + post.base_code + "\n"

                for(codes in post.conversion_ratesonversions){
                    content += "code: " + codes.key + "\n"
                    content += "value: " + codes.value + "\n"
                }
                myJsonTxt.append(content)
             */
            }

            override fun onFailure(call: Call<Posts>, t: Throwable) {
                myJsonTxt.setText(t.message)
            }
        })

    }

}
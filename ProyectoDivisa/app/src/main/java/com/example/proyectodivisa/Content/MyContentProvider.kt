package com.example.proyectodivisa.Content

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import com.example.proyectodivisa.Database.MonedaDatabase
import com.example.proyectodivisa.Repository.MonedaRepository
import com.example.proyectodivisa.Repository.Myapplication

private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {

    addURI("com.example.proyectodivisa", "monedas", 1)

    addURI("com.example.proyectodivisa", "monedas/#", 2)

    addURI("com.example.proyectodivisa", "monedas/*", 3)
}
const val _ID = "_id"
const val CODE = "name"

class MyContentProvider : ContentProvider() {

    lateinit var repository: MonedaRepository
    lateinit var db: MonedaDatabase
    override fun onCreate(): Boolean {
        //TODO("Not yet implemented")
        val context = context ?: return false
        val dbHelper = MyDatabaseHelper(context)
        db = dbHelper.writableDatabase
        return db != null
      //  repository =  (context as Myapplication).repositoryMoneda
       // db =  (context as Myapplication).database
       // return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val qb = SQLiteQueryBuilder()
        qb.tables = MyDatabaseHelper.TABLE_NAME
        //TODO("Not yet implemented")


        var cursor: Cursor? = null

        when(sUriMatcher.match(uri)){
            //"content://com.example.proyectodivisa/monedas"
            //query / insert
            1 -> {
                cursor = db.MonedaDao().getAllCursor()

            }
            //"content://com.example.proyectodivisa/monedas/*"
            //query
            2 -> {

            }
            //"content://com.example.proyectodivisa/monedas/#"
            //query / update  /  delete
            3 -> {

            }
            else -> {
                throw IllegalArgumentException("Unknown URI $uri")
            }
        }
        var order = sortOrder
        if (order == null || order.isEmpty()) {
            order = NAME
        }
        val c = qb.query(db, projection, selection, selectionArgs, null, null, order)
        c.setNotificationUri(context?.contentResolver, uri)
        return cursor
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }


    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        //TODO("Not yet implemented")
        when( sUriMatcher.match(uri)){

            //"content://net.ivanvega.proyectodivisacontentprividera/monedas"
            //query / insert
            1 -> {
                //ir  a la bd y traer el getall
            }

            //"content://net.ivanvega.proyectodivisacontentprividera/monedas/*"
            //query
            2 -> {

            }

            //"content://net.ivanvega.proyectodivisacontentprividera/monedas/#"
            //query / update  /  delete
            3 -> {
                //mandar llamar el dao para un delete
            }
            else -> {

            }

        }
        return  0
    }

    override fun update(uri: Uri,values: ContentValues?,selection: String?,selectionArgs: Array<out String>?
    ): Int {
        //TODO("Not yet implemented")
        when( sUriMatcher.match(uri)){

            //"content://net.ivanvega.proyectodivisacontentprividera/monedas"
            //query / insert
            1 -> {
                //ir  a la bd y traer el getall
            }

            //"content://net.ivanvega.proyectodivisacontentprividera/monedas/*"
            //query
            2 -> {

            }

            //"content://net.ivanvega.proyectodivisacontentprividera/monedas/#"
            //query / update  /  delete
            3 -> {
                //Mandar llamar el dao para actuailizar un registro por ID
            }
            else -> {

            }

        }
        return  0
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        //TODO("Not yet implemented")
        when( sUriMatcher.match(uri)){

            //"content://net.ivanvega.proyectodivisacontentprividera/monedas"
            //query / insert
            1 -> {
                //ir  a la bd y llamar insert. Mapear ContentValues con Pojo

            }

            //"content://net.ivanvega.proyectodivisacontentprividera/monedas/*"
            //query
            2 -> {

            }

            //"content://net.ivanvega.proyectodivisacontentprividera/monedas/#"
            //query / update  /  delete
            3 -> {

            }
            else -> {

            }

        }
        return null
    }


}
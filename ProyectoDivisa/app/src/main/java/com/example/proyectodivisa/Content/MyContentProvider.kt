package com.example.proyectodivisa.Content

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.proyectodivisa.Database.Moneda
import com.example.proyectodivisa.Database.MonedaDao
import com.example.proyectodivisa.Database.MonedaDatabase
import com.example.proyectodivisa.Repository.MonedaRepository
import com.example.proyectodivisa.Repository.Myapplication

/*
private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {

    addURI("com.example.proyectodivisa", "monedas", 1)

    addURI("com.example.proyectodivisa", "monedas/#", 2)

    addURI("com.example.proyectodivisa", "monedas/*", 3)
}
*/

 */

class MyContentProvider : ContentProvider() {

    var dataBase="moneda"
    var provider ="com.example.proyectodivisa.Content.MyContentProvider"
    var tabla = "moneda"
    var code="code"
    var value=0.0

    var myUriCode=1

     lateinit var MyDB : SQLiteDatabase

     lateinit var uriMatcher: UriMatcher

    //lateinit var repository: MonedaRepository
    lateinit var db: MonedaDatabase


    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(): Boolean {
     //   repository = (context as Myapplication).repositoryMoneda //context
        db = (context as Myapplication).database //helper

        uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        uriMatcher.addURI(provider,tabla,myUriCode)
        MyDB = requireContext().openOrCreateDatabase(dataBase, Context.MODE_PRIVATE, null)
        return true
    }

    override fun query(uri: Uri,projection: Array<out String>?,selection: String?,
                       selectionArgs: Array<out String>?,sortOrder: String?): Cursor? {
        var cursor: Cursor? = null
        when (uriMatcher.match(uri)) {
            1 -> {
                cursor = db.MonedaDao().getAllCursor()
                cursor.setNotificationUri(context?.contentResolver, uri)
            }
            else -> {
                throw IllegalArgumentException("Unknown URI $uri")
            }
        }
            return cursor
    }

    override fun getType(uri: Uri): String? {
        when (uriMatcher.match(uri)) {
            1 -> {
                return provider + "/" + tabla
            }
            else -> {
                throw IllegalArgumentException("Unknown URI $uri")
            }
        }
    }
override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
    //TODO("Not yet implemented")
    when( uriMatcher.match(uri)){

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
    when( uriMatcher.match(uri)){

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
    when( uriMatcher.match(uri)){

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
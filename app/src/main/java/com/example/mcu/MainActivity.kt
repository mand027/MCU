package com.example.mcu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import org.json.JSONArray
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var jAvenger:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun leerJSON():String?{
        var jContenido:String? = null

        try {
            val inputS = assets.open("avengers.json")
            jContenido = inputS.bufferedReader().use { it.readLine() }
        }catch ( ex: Exception ) {
            ex.printStackTrace()
            Toast.makeText(this, "PUM! no hay", Toast.LENGTH_LONG).show()
        }

        return jContenido
    }

    private fun getDataSet(): ArrayList<MCUDude>{
        val dudes = ArrayList<MCUDude>()

        jAvenger = leerJSON()
        Log.i("edu.daec.edu", jAvenger)
        val jArray = JSONArray(jAvenger)
        for(i in 0..jArray.length()-1){
            val jobject = jArray.getJSONObject(i)
            val name = jobject.getString("name/alias")
            val notes = jobject.getString("notes")
            dudes.add(MCUDude(name, notes))

        }
        return dudes
    }
}

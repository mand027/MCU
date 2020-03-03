package com.example.mcu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.lang.Exception
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class MainActivity : AppCompatActivity() {

    var jAvenger:String? = null
    private lateinit var mcuDudeAdapter : MCUDudeRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRecyclerView() // adapter instanciado

        //mcuDudeAdapter.setData(getDataSet())
        MCUMarvelVolley(getMarvelAPIUrl(), this, mcuDudeAdapter).callMarvelApi()
    }

    private fun setRecyclerView(){
        recycler_view_mcu.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            mcuDudeAdapter = MCUDudeRecyclerAdapter()
            adapter = mcuDudeAdapter
        }
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
            if(name!=null) dudes.add(MCUDude(name, notes))

        }
        return dudes
    }

    fun String.md5():String{
        val md5Al = MessageDigest.getInstance("MD5")
        return BigInteger(1, md5Al.digest(toByteArray())).toString(16).padStart(32, '0')
    }

    fun getMarvelAPIUrl(): String{
        val tString = Timestamp(System.currentTimeMillis()).toString()
        val hString = tString + "7ea50c99510d0490d8d446668c324c12f29ffc81" + "0c5a5356991eb0cf559b226590462d43"
        val hash = hString.md5()

        var marvelAPI : String = "https://gateway.marvel.com:443/v1/public/characters?ts=" +
                tString +
                "&limit=100&apikey=7ea50c99510d0490d8d446668c324c12f29ffc81&hash="+hash
        return marvelAPI

    }
}

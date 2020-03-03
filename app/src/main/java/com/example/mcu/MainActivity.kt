package com.example.mcu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var jAvenger:String? = null
    private lateinit var mcuDudeAdapter : MCUDudeRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRecyclerView() // adapter instanciado
        mcuDudeAdapter.setData(getDataSet())
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
}

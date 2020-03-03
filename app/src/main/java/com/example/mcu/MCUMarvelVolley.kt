package com.example.mcu

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MCUMarvelVolley(val url:String, val contexto:Context, val mcuAdapter: MCUDudeRecyclerAdapter){
    val queue = Volley.newRequestQueue(contexto)

    fun callMarvelApi(){
        val dataHeroes = ArrayList<MCUDude>()

        val requestMarvel = JsonObjectRequest(
            Request.Method.GET,
            url, null,
            Response.Listener<JSONObject>{
                response ->
                val heroes = response.getJSONObject("data").getJSONArray("results")
                for (i in 0..heroes.length()-1){
                    val personaje = heroes.getJSONObject(i)
                    val mcuDude = MCUDude(personaje.getString("name"), personaje.getString("description"))
                    dataHeroes.add(mcuDude)
                }
                mcuAdapter.setData(dataHeroes)
            },
            Response.ErrorListener {
                Toast.makeText(contexto, "hubo un ERROR", Toast.LENGTH_LONG).show()
            })

        queue.add(requestMarvel)
    }
}
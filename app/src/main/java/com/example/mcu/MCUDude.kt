package com.example.mcu

import android.app.AliasActivity

class MCUDude(val alias: String, val notes: String) {
    public var name: String?= null
    public var heroNotes: String?= null
    init{
        name = alias
        heroNotes = notes
    }

}
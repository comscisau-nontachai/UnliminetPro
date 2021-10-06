package com.becomedev.unliminetpro.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.becomedev.unliminetpro.R
import com.becomedev.unliminetpro.ui.main.MainActivity

class SlashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slash_screen)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
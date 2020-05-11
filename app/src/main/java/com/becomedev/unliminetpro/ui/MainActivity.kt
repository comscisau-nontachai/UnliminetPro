package com.becomedev.unliminetpro.ui

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.becomedev.unliminetpro.R
import com.becomedev.unliminetpro.ui.adapter.ViewPagerAdapter
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Stetho.initializeWithDefaults(this)

        initViewPager()
    }

    private fun initViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        view_pager.adapter = adapter
        tabs_main.setupWithViewPager(view_pager)
    }
}

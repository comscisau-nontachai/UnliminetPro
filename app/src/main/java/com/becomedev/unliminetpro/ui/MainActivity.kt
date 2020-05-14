package com.becomedev.unliminetpro.ui

import android.content.DialogInterface
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.becomedev.unliminetpro.R
import com.becomedev.unliminetpro.ui.adapter.ViewPagerAdapter
import com.facebook.stetho.Stetho
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Stetho.initializeWithDefaults(this)

        initViewPager()

        supportActionBar?.setDisplayShowHomeEnabled(true)

        setActionbarStyle(R.string.str_true,R.color.colorTrueHead,R.drawable.true_icon)
        tabs_main.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tabs_main.selectedTabPosition){
                    0 -> setActionbarStyle(R.string.str_true,R.color.colorTrueHead,R.drawable.true_icon)
                    1 -> setActionbarStyle(R.string.str_ais,R.color.colorAisHead,R.drawable.ais_icon)
                    2 -> setActionbarStyle(R.string.str_dtac,R.color.colorDtacHead,R.drawable.dtac_icon)

                }
            }

        })
    }

    private fun initViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        view_pager.adapter = adapter
        tabs_main.setupWithViewPager(view_pager)
    }

    private fun setActionbarStyle(strTitleRes:Int,colorRes:Int,iconRes:Int){
        supportActionBar.apply {
            title = this@MainActivity.getString(strTitleRes)
            this?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(applicationContext,colorRes)))
            this?.setIcon(iconRes)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_network,menu)
        return true

    }

    override fun onBackPressed() {

        AlertDialog.Builder(this).apply {
            setTitle("ออกจากแอป")
            setMessage("คุณต้องการออกจากแอป?")
            setPositiveButton("ออก"
            ) { dialog, which ->
                finish()
            }
            setNegativeButton("ไม่"){
                dialog, which ->
                dialog.dismiss()
            }
        }.also { it.show() }

    }
}

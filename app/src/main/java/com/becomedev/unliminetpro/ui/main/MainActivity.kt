package com.becomedev.unliminetpro.ui.main

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.becomedev.unliminetpro.BuildConfig
import com.becomedev.unliminetpro.R
import com.becomedev.unliminetpro.data.base.BaseActivity
import com.becomedev.unliminetpro.data.constant.ApplicationConstantKey
import com.becomedev.unliminetpro.data.enum.PromotionNetworkEnum
import com.becomedev.unliminetpro.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : BaseActivity<ActivityMainBinding>(){
    override fun getLayoutId(): Int = R.layout.activity_main

    override fun setUp() {
        super.setUp()

        initView()
    }

    private fun initView() {

        val navController: NavController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupWithNavController(binding.bnvMain, navController)

        val bundle = Bundle()
        bundle.putSerializable(ApplicationConstantKey.KEY_PROMOTION_TYPE,PromotionNetworkEnum.TRUE)
        navController.navigate(R.id.action_true,bundle)

        binding.bnvMain.itemIconTintList = null
        binding.bnvMain.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.action_true ->{
                    bundle.putSerializable(ApplicationConstantKey.KEY_PROMOTION_TYPE,PromotionNetworkEnum.TRUE)
                    navController.navigate(R.id.action_true,bundle)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_ais ->{
                    bundle.putSerializable(ApplicationConstantKey.KEY_PROMOTION_TYPE,PromotionNetworkEnum.AIS)
                    navController.navigate(R.id.action_ais,bundle)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_dtac ->{
                    bundle.putSerializable(ApplicationConstantKey.KEY_PROMOTION_TYPE,PromotionNetworkEnum.DTAC)
                    navController.navigate(R.id.action_dtac,bundle)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_network,menu)
        menu?.getItem(0)?.title = "เวอร์ชั่น ${BuildConfig.VERSION_NAME}"
        return true
    }

    override fun onBackPressed() {
        MaterialAlertDialogBuilder(this).apply {
            setTitle("ออกจากแอป ${applicationInfo.loadLabel(packageManager)}")
            setMessage("คุณต้องการออกจากแอป?")
            setPositiveButton("ออก"){ dialog,_ ->
                finishAffinity()
            }
            setNegativeButton("ไม่"){ dialog , _ ->
                dialog.dismiss()
            }
        }.show()
    }

}


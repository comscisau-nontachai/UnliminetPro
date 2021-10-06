package com.becomedev.unliminetpro.data.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    var isBindView = false
    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        getExtra(intent.extras)
        setUp()
        subscribe()
        isBindView = true
    }

    abstract fun getLayoutId(): Int

    open fun getExtra(bundle: Bundle?) {}

    open fun setUp() {}

    open fun subscribe() {
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
package com.becomedev.unliminetpro.di

import com.becomedev.unliminetpro.network.builder.RetrofitBuilder
import com.becomedev.unliminetpro.repository.PackageRepository
import com.becomedev.unliminetpro.ui.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { RetrofitBuilder(androidContext()).api() }
    single { PackageRepository(get()) }

    viewModel {
        MainViewModel(get())
    }
}
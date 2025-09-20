package com.becomedev.unliminetpro.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.becomedev.unliminetpro.data.enums.NetworkCompEnum
import com.becomedev.unliminetpro.data.model.PromotionModel
import com.becomedev.unliminetpro.network.UiState
import com.becomedev.unliminetpro.repository.PackageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PackageRepository): ViewModel() {
    private val _truemove = MutableStateFlow<UiState<PromotionModel>>(UiState.Loading)
    val truemove: StateFlow<UiState<PromotionModel>> = _truemove

    init {
        getPackageTrueMove()
    }

    fun getPackageTrueMove(companyNameId : Int = NetworkCompEnum.TRUE.ordinal) {
        viewModelScope.launch {
            _truemove.value = UiState.Loading

            try {
                when(companyNameId){
                    NetworkCompEnum.TRUE.ordinal -> {
                        fetchTrueMoveData()
                    }
                    NetworkCompEnum.AIS.ordinal -> {
                        fetchAisData()
                    }
                    NetworkCompEnum.DTAC.ordinal -> {
                        fetchDtacData()
                    }
                }
            } catch (e: Exception) {
                // 3b. ถ้าล้มเหลว อัปเดต State เป็น Error พร้อมข้อความ
                _truemove.value = UiState.Error(e.message ?: "An unknown error occurred")
            }
        }

    }

    private suspend fun fetchTrueMoveData(){
        val result = repository.getPackageTrueMove()
        _truemove.value = result
    }

    private suspend fun fetchAisData(){
        val result = repository.getPackageAis()
        _truemove.value = result
    }

    private suspend fun fetchDtacData(){
        val result = repository.getPackageDtac()
        _truemove.value = result
    }



}



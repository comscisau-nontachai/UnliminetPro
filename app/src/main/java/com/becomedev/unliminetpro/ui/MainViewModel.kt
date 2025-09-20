package com.becomedev.unliminetpro.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
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

    fun getPackageTrueMove() {
        viewModelScope.launch {
            _truemove.value = UiState.Loading
            try {
                // 2. เรียกใช้ Repository เพื่อดึงข้อมูล
                val result = repository.getPackageTrueMove()
                _truemove.value = result
            } catch (e: Exception) {
                // 3b. ถ้าล้มเหลว อัปเดต State เป็น Error พร้อมข้อความ
                _truemove.value = UiState.Error(e.message ?: "An unknown error occurred")
            }
        }

    }

}

class MainViewModelFactory(private val repo: PackageRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


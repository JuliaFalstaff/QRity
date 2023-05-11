package com.jfalstaff.qrity.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jfalstaff.qrity.data.RepositoryImpl
import com.jfalstaff.qrity.domain.ColorCode
import com.jfalstaff.qrity.domain.GetColorListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateQrCodeViewModel(

): ViewModel() {

    private val getColorListUseCase: GetColorListUseCase = GetColorListUseCase(repository = RepositoryImpl())

    private var _state = MutableLiveData<List<ColorCode>>()
    val state: LiveData<List<ColorCode>> = _state

     fun getColorList() {
        val list = getColorListUseCase.invoke()
        _state.value = list
    }
}
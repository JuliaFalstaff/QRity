package com.jfalstaff.qrity.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jfalstaff.qrity.data.RepositoryImpl
import com.jfalstaff.qrity.domain.GetColorListUseCase

class CreateQrCodeViewModel(

) : ViewModel() {

    private val getColorListUseCase: GetColorListUseCase =
        GetColorListUseCase(repository = RepositoryImpl())

    private var _state = MutableLiveData<List<String>>()
    val state: LiveData<List<String>> = _state

    fun getColorList2() {
        val list = getColorListUseCase.invoke()
        _state.value = list
    }
}
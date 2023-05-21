package com.jfalstaff.qrity.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jfalstaff.qrity.domain.GetColorListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateQrCodeViewModel @Inject constructor(
    private val getColorListUseCase: GetColorListUseCase
) : ViewModel() {

    private var _state = MutableLiveData<List<String>>()
    val state: LiveData<List<String>> = _state

    fun getColorList() {
        val list = getColorListUseCase.invoke()
        _state.value = list
    }
}
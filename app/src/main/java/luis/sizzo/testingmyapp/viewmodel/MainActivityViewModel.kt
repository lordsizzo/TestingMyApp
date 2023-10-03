package luis.sizzo.testingmyapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import luis.sizzo.testingmyapp.model.UI_State
import androidx.lifecycle.*
import kotlinx.coroutines.*
import luis.sizzo.testingmyapp.model.MainActivityRepository


class MainActivityViewModel: ViewModel() {

    private val repository = MainActivityRepository()

    private val _stringResponse = MutableLiveData<UI_State>()
    val getStringResponse: MutableLiveData<UI_State>
        get() = _stringResponse

    private val _flipStringResponse = MutableLiveData<UI_State>()
    val getFlipStringResponse: MutableLiveData<UI_State>
        get() = _flipStringResponse

    fun getStringEncripted(descryptString: String) {
        viewModelScope.launch {
            repository.getStringEncripted(descryptString).collect {
                _stringResponse.postValue(it)
            }
        }
    }

    fun getSplitEncripted(descryptString: String) {
        viewModelScope.launch {
            repository.getFlipStringEncripted(descryptString).collect {
                _flipStringResponse.postValue(it)
            }
        }
    }
}
package com.example.navigationmvvm.screens.b.hello

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.navigationmvvm.R
import com.example.navigationmvvm.navigator.Navigator
import com.example.navigationmvvm.screens.b.base.BaseScreen
import com.example.navigationmvvm.screens.b.base.BaseViewModel
import com.example.navigationmvvm.screens.b.edit.EditFragment

//реализация viewmodel для стартового экрана
class HelloViewModel(
    private val navigator: Navigator,
    screen: HelloFragment.Screen
): BaseViewModel() {

    private val _currentMessageLiveData = MutableLiveData<String>()
    val currentMessageLiveData: LiveData<String> = _currentMessageLiveData

    init {
        _currentMessageLiveData.value = navigator.getString(R.string.hello_world)
    }

    override fun onResult(result: Any) {
        if (result is String){
            _currentMessageLiveData.value = result
        }
    }

    fun onEditPressed(){
        navigator.launch(EditFragment.Screen(initialValue = currentMessageLiveData.value!!))
    }
}
package com.example.navigationmvvm.screens.b.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.navigationmvvm.Event
import com.example.navigationmvvm.R
import com.example.navigationmvvm.navigator.Navigator
import com.example.navigationmvvm.screens.b.base.BaseViewModel
import com.example.navigationmvvm.screens.b.edit.EditFragment.Screen

//реализация viewmodel для экрана редактирования
class EditViewModel(
    private val navigator: Navigator,
    screen: Screen
): BaseViewModel() {

    private val _initialMessageEvent = MutableLiveData<Event<String>>()
    val initialMessageEvent: LiveData<Event<String>> = _initialMessageEvent

    init {
        _initialMessageEvent.value = Event(screen.initialValue)
    }

    fun onSavePressed(message: String){
        if (message.isBlank()){
            navigator.toast(R.string.empty_message)

            return
        }

        navigator.goBack(message)
    }

    fun onBackPressed(){
        navigator.goBack()
    }
}
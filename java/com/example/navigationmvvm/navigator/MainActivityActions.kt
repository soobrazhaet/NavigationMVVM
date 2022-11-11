package com.example.navigationmvvm.navigator

import com.example.navigationmvvm.MainActivity

typealias MainActivityAction = (MainActivity) -> Unit

class MainActivityActions {
    var mainActivity: MainActivity? = null
        set(activity){
            field = activity

            if (activity != null){
                actions.forEach { it(activity) }
                actions.clear()
            }
        }
    private val actions = mutableListOf<MainActivityAction>()

    //добавление действий
    operator fun invoke(action: MainActivityAction){
        val activity = this.mainActivity

        if (mainActivity == null){
            actions += action
        }
        else{
            if (activity != null) {
                action(activity)
            }
        }
    }

    //метод очистки действий
    fun clear(){
        actions.clear()
    }
}
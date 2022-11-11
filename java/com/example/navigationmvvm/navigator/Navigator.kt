package com.example.navigationmvvm.navigator

import androidx.annotation.StringRes
import com.example.navigationmvvm.screens.b.base.BaseScreen

interface Navigator {

    //запуск нового экрана
    fun launch(screen: BaseScreen)

    //выход на предыдущий экран
    fun goBack(result: Any? = null)

    //вывод сообщений на экран
    fun toast(@StringRes messageRes: Int)

    //получение данных из viewmodel
    fun getString(@StringRes messageRes: Int): String
}
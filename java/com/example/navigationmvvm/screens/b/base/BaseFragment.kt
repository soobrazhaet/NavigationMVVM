package com.example.navigationmvvm.screens.b.base

import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    //управляет этим фрагментом
    abstract val viewModel: BaseViewModel
}
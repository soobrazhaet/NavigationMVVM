package com.example.navigationmvvm.screens.b.base

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.navigationmvvm.navigator.ARG_SCREEN
import com.example.navigationmvvm.navigator.MainNavigator
import com.example.navigationmvvm.navigator.Navigator
import com.example.navigationmvvm.screens.b.base.BaseFragment
import com.example.navigationmvvm.screens.b.hello.HelloFragment

class ViewModelFactory(
    private val screen: BaseScreen,
    private val fragment: BaseFragment
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val hostActivity = fragment.requireActivity()
        val application = hostActivity.application
        val navigatorProvider = ViewModelProvider(hostActivity,
            ViewModelProvider.AndroidViewModelFactory(application)
        )
        val navigator = navigatorProvider[MainNavigator::class.java]

        val constructor = modelClass.getConstructor(Navigator::class.java, screen::class.java)
        return constructor.newInstance(navigator, screen)
    }
}

inline fun <reified VM: ViewModel> BaseFragment.screenViewModel() = viewModels<VM> {
    val screen = requireArguments().getSerializable(ARG_SCREEN) as BaseScreen
    ViewModelFactory(screen, this)
}
package com.example.navigationmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.navigationmvvm.navigator.MainNavigator
import com.example.navigationmvvm.screens.b.base.BaseFragment
import com.example.navigationmvvm.screens.b.hello.HelloFragment

class MainActivity : AppCompatActivity() {

    private val navigator by viewModels<MainNavigator> { ViewModelProvider.AndroidViewModelFactory(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null){
            navigator.launchFragment(this, HelloFragment.Screen(), addToBackStack = false)
        }

        //регистрация callback
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentCallbacks, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        //удаление callback
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentCallbacks)
    }

    //для перехода на предыдущий экран через стрелку
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onResume() {
        super.onResume()
        navigator.whenActivityActive.mainActivity = this
    }

    override fun onPause() {
        super.onPause()
        navigator.whenActivityActive.mainActivity = null
    }

    //прослушка фрагментов
    private val fragmentCallbacks = object: FragmentManager.FragmentLifecycleCallbacks(){
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            if (supportFragmentManager.backStackEntryCount > 8){
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
            else{
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }

            val result = navigator.result.value?.getValue() ?: return

            if (f is BaseFragment){
                f.viewModel.onResult(result)
            }
        }
    }
}
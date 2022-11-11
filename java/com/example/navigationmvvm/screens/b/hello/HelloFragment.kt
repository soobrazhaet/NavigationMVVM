package com.example.navigationmvvm.screens.b.hello

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.navigationmvvm.databinding.FragmentHelloBinding
import com.example.navigationmvvm.screens.b.base.BaseFragment
import com.example.navigationmvvm.screens.b.base.BaseScreen
import com.example.navigationmvvm.screens.b.base.BaseViewModel
import com.example.navigationmvvm.screens.b.base.screenViewModel

class HelloFragment: BaseFragment() {

    class Screen: BaseScreen

    override val viewModel by screenViewModel<HelloViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHelloBinding.inflate(inflater, container, false)

        //передача управления viewmodel
        binding.editButton.setOnClickListener{
            viewModel.onEditPressed()
        }

        //прослушка livedata
        viewModel.currentMessageLiveData.observe(viewLifecycleOwner){
            binding.valueTextView.text = it
        }

        return binding.root

    }
}
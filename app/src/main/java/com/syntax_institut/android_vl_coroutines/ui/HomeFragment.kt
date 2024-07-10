package com.syntax_institut.android_vl_coroutines.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.syntax_institut.android_vl_coroutines.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.timer.observe(viewLifecycleOwner) { timer ->
            binding.tvTimer.text = timer.toString()
        }

        binding.btStart.setOnClickListener {
            viewModel.toggleTimer()
        }

        binding.btFaster.setOnClickListener {
            viewModel.faster()
        }

        binding.btSlower.setOnClickListener {
            viewModel.slower()
        }
    }

}
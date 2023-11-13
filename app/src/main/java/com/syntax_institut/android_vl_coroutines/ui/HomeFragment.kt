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

        // Observer um Timer zu beobachten
        viewModel.timer.observe(viewLifecycleOwner) {
            binding.tvTimer.text = it.toString()
        }

        // Listener um Timer entweder zu starten oder zu stoppen
        binding.btStart.setOnClickListener {
            viewModel.toggleTimer()
        }

        // Listener um Timer Delay zu erhöhen
        binding.btFaster.setOnClickListener {
            viewModel.faster()
        }



    }

}
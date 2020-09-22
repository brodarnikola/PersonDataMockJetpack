package com.vjezba.persondatamockjetpack.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vjezba.persondatamockjetpack.R
import com.vjezba.persondatamockjetpack.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.activity_main.*

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        context ?: return binding.root

        activity?.toolbar?.title = getString(R.string.home_title)


        return binding.root
    }

    override fun onResume() {
        super.onResume()

        btnIncreaseNumber.setOnClickListener {
        }

        btnChooseLanguage.setOnClickListener {
        }

    }

}
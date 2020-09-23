package com.vjezba.persondatamockjetpack.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vjezba.persondatamockjetpack.R
import com.vjezba.persondatamockjetpack.databinding.FragmentHomeFirstBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home_first.*

class HomeFirstFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeFirstBinding.inflate(inflater, container, false)
        context ?: return binding.root

        activity?.toolbar?.title = getString(R.string.home_title)

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        btnMockApi.setOnClickListener {
        }

        btnRoom.setOnClickListener {
            val direction = HomeFirstFragmentDirections.homeFragmentToRoomDisplayAllUsersFragment()
            findNavController().navigate(direction)
        }

    }

}
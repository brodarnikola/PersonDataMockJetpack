/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vjezba.persondatamockjetpack.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.vjezba.persondatamockjetpack.R
import com.vjezba.persondatamockjetpack.databinding.FragmentRoomDisplayAllUsersBinding
import com.vjezba.persondatamockjetpack.ui.adapters.AllPersonsAdapter
import com.vjezba.persondatamockjetpack.viewmodels.RoomDisplayAllUsersViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomDisplayAllUsersFragment : Fragment() {


    private val viewModel : RoomDisplayAllUsersViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRoomDisplayAllUsersBinding.inflate(inflater, container, false)
        context ?: return binding.root

        activity?.toolbar?.title = "Room display all users"

        val adapter =
            AllPersonsAdapter()
        binding.personsList.adapter = adapter
        subscribeUi(adapter)

        return binding.root
    }

    private fun subscribeUi(adapter: AllPersonsAdapter) {
        viewModel.allPersons.observe(viewLifecycleOwner,  Observer { plants ->
            if( plants.isNotEmpty() ) {
                adapter.submitList(plants)
            }
            else {

            }
        })
    }

}

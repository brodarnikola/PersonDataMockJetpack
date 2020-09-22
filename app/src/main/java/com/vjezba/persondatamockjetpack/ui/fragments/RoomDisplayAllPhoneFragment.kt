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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.vjezba.domain.model.AllPhones
import com.vjezba.persondatamockjetpack.databinding.FragmentRoomDisplayAllPhoneBinding
import com.vjezba.persondatamockjetpack.ui.adapters.AllPhonesAdapter
import com.vjezba.persondatamockjetpack.viewmodels.RoomDisplayAllPhonesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_room_display_all_phone.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RoomDisplayAllPhoneFragment : Fragment() {

    private val args: RoomDisplayAllPhoneFragmentArgs by navArgs()

    private val viewModel : RoomDisplayAllPhonesViewModel by viewModel{
        parametersOf( args.personId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRoomDisplayAllPhoneBinding.inflate(inflater, container, false)
        context ?: return binding.root

        activity?.toolbar?.title = "Room all phones from user"

        val adapter =
            AllPhonesAdapter( { phone: AllPhones -> setDeletePhoneClickListener(phone) } )
        binding.phonesList.adapter = adapter
        subscribeUi(adapter)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        addNewPhone()
    }

    private fun addNewPhone() {
        btnInsertPhone.setOnClickListener {
            lifecycleScope.launch {
                viewModel.addNewPhone(
                    etPhone.text.toString(),
                    etOperater.text.toString(),
                    args.personId
                )
            }
        }
    }

    private fun setDeletePhoneClickListener(phone: AllPhones) {
        viewModel.deleteSelectedPhone(phone)
    }

    private fun subscribeUi(adapter: AllPhonesAdapter) {
        viewModel.allPhones.observe(viewLifecycleOwner,  Observer { phones ->
            if( phones.isNotEmpty() ) {
                phones_list.visibility = View.VISIBLE
                tvNoUserSaved.visibility = View.GONE
                adapter.submitList(phones)
            }
            else {
                phones_list.visibility = View.GONE
                tvNoUserSaved.visibility = View.VISIBLE
            }
        })
    }

}

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
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.vjezba.persondatamockjetpack.R
import com.vjezba.persondatamockjetpack.databinding.FragmentRoomPersonDetailsBinding
import com.vjezba.persondatamockjetpack.ui.activities.MainActivity
import com.vjezba.persondatamockjetpack.ui.dialog.DeleteUser
import com.vjezba.persondatamockjetpack.ui.dialog.RoomDeleteUserDialog
import com.vjezba.persondatamockjetpack.viewmodels.RoomPersonDetailsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_room_person_details.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


/**
 * A fragments representing a single Plant detail screen.
 */
class RoomPersonDetailsFragment : Fragment(), DeleteUser {

    private val args: RoomPersonDetailsFragmentArgs by navArgs()

    private val roomPersonDetailsViewModel : RoomPersonDetailsViewModel by viewModel {
        parametersOf( args.personId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentRoomPersonDetailsBinding>(
            inflater,
            R.layout.fragment_room_person_details,
            container,
            false
        ).apply {
            viewModel = roomPersonDetailsViewModel

            lifecycleOwner = this@RoomPersonDetailsFragment

            activity?.toolbar?.title = "Room user data"
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        btnInsertUser.setOnClickListener {
            updateChangeUserData()
        }

        btnDeleteUser.setOnClickListener {
            val deleteUserDialog =
                RoomDeleteUserDialog(args.personId, this@RoomPersonDetailsFragment)
            deleteUserDialog.show(
                (requireActivity() as MainActivity).supportFragmentManager,
                "")
        }

        fabAddNewPhone.setOnClickListener {
            val direction =
                RoomPersonDetailsFragmentDirections.userDetailsFragmentToAllPhonesFragment(args.personId)
            findNavController().navigate(direction)
        }
    }

    private fun updateChangeUserData() {
        lifecycleScope.launch {
            val numberOfUpdateRows = roomPersonDetailsViewModel.updateChangeUserDetails(
                args.personId,
                etName.text.toString(),
                etDescription.text.toString(),
                etAddress.text.toString()
            )
            withContext(Dispatchers.Main) {
                if (numberOfUpdateRows > 0) {
                    val snackbar = Snackbar
                        .make(coordinatorLayout, "Successfully updated", Snackbar.LENGTH_LONG)
                    snackbar.show()
                }
                else{
                    val snackbar = Snackbar
                        .make(coordinatorLayout, "Update failed updated", Snackbar.LENGTH_LONG)
                    snackbar.show()
                }
            }
        }
    }

    override fun deleteUser(personId: Int) {
        lifecycleScope.launch {
            val numberOfUpdateRows = roomPersonDetailsViewModel.deleteUser(
                args.personId
            )
            withContext(Dispatchers.Main) {
                if (numberOfUpdateRows > 0) {
                    val direction =
                        RoomPersonDetailsFragmentDirections.userDetailsFragmentToAllUsersFragment()
                    findNavController().navigate(direction)
                    Toast.makeText(requireContext(), "Successfully deleted", Toast.LENGTH_LONG)
                        .show()
                } else {
                    val snackbar = Snackbar
                        .make(coordinatorLayout, "Deleted failed", Snackbar.LENGTH_LONG)
                    snackbar.show()
                }
            }
        }
    }


}

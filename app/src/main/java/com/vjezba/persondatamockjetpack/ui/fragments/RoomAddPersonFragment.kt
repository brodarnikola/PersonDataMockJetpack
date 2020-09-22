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

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.vjezba.persondatamockjetpack.R
import com.vjezba.persondatamockjetpack.databinding.FragmentAddPersonBinding
import com.vjezba.persondatamockjetpack.viewmodels.PersonAddViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_room_person_details.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A fragments representing a single Plant detail screen.
 */
class RoomAddPersonFragment : Fragment() {


    private val personAddViewModel : PersonAddViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentAddPersonBinding>(
            inflater,
            R.layout.fragment_add_person,
            container,
            false
        ).apply {
            viewModel = personAddViewModel

            lifecycleOwner = this@RoomAddPersonFragment

            activity?.toolbar?.title = "Room add new user"
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        btnInsertUser.setOnClickListener {
            addNewUser()
        }
    }

    private fun addNewUser() {
        lifecycleScope.launch(Dispatchers.IO) {
            val lastPersonID = personAddViewModel.findLastUserId()
            val numberOfUpdateRows = personAddViewModel.addNewUser(
                lastPersonID.toInt(),
                etName.text.toString(),
                etDescription.text.toString(),
                etAddress.text.toString()
            )
            withContext(Dispatchers.Main) {
                if (numberOfUpdateRows > 0) {
                    hideKeyboard(requireActivity())
                    val snackbar = Snackbar
                        .make(coordinatorLayout, "Successfully added new user", Snackbar.LENGTH_LONG)
                    snackbar.show()
                }
                else{
                    val snackbar = Snackbar
                        .make(coordinatorLayout, "Failed to add new user", Snackbar.LENGTH_LONG)
                    snackbar.show()
                }
            }
        }
    }

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}

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
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.vjezba.persondatamockjetpack.databinding.FragmentMockDisplayAllUsersBinding
import com.vjezba.persondatamockjetpack.mock.LoginResponse
import com.vjezba.persondatamockjetpack.mock.NetworkClient
import com.vjezba.persondatamockjetpack.ui.adapters.AllPersonsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MockDisplayAllUsersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMockDisplayAllUsersBinding.inflate(inflater, container, false)
        context ?: return binding.root

        activity?.toolbar?.title = "Mock display all users"

        val adapter =
            AllPersonsAdapter()
        binding.personList.adapter = adapter
        //subscribeUi(adapter)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        addNewUserS()
    }

    private fun addNewUserS() {
        NetworkClient.create().login().enqueue(object: Callback<List<LoginResponse>> {
            override fun onFailure(call: Call<List<LoginResponse>>, t: Throwable) {
                Log.d("Error",t.toString())
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<LoginResponse>>, response: Response<List<LoginResponse>>) {

                //Log.d("aa", "Owner action status: ${assinedUsers.joinToString { "-" + it.name + ", " + it.ownerActionStatus + ", " + it.isVendor  }}")
                Log.d("Success","AAAAAA: " + response.body()?.joinToString { it.name + "-" + it.address + "\n" }  )
                Toast.makeText(requireContext(), "Success: ${response.body()}", Toast.LENGTH_LONG).show()
            }
        })
    }
/*
    private fun subscribeUi(adapter: AllPersonsAdapter) {
        viewModel.allPersons.observe(viewLifecycleOwner,  Observer { plants ->
            if( plants.isNotEmpty() ) {
                person_list.visibility = View.VISIBLE
                tvNoUserSaved.visibility = View.GONE
                adapter.submitList(plants)
            }
            else {
                person_list.visibility = View.GONE
                tvNoUserSaved.visibility = View.VISIBLE
            }
        })
    }*/

}

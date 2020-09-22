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
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.vjezba.persondatamockjetpack.R
import com.vjezba.persondatamockjetpack.databinding.FragmentRoomPersonDetailsBinding
import com.vjezba.persondatamockjetpack.viewmodels.PersonDetailsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * A fragments representing a single Plant detail screen.
 */
class RoomPersonDetailsFragment : Fragment() {

    private val args: RoomPersonDetailsFragmentArgs by navArgs()

    /*private val languageDetailsViewModel: LanguageDetailsViewModel by viewModels {
        InjectorUtils.provideLanguageDetailsViewModelFactory(requireActivity(), args.languagesId)
    }*/

    private val languageDetailsViewModel : PersonDetailsViewModel by viewModel {
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
            viewModel = languageDetailsViewModel

            lifecycleOwner = this@RoomPersonDetailsFragment

            activity?.toolbar?.title = "Room user data"
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun subscribeUi() {
        languageDetailsViewModel.personDetails.observe(viewLifecycleOwner,  Observer { plants ->
        })
    }



    // FloatingActionButtons anchored to AppBarLayouts have their visibility controlled by the scroll position.
    // We want to turn this behavior off to hide the FAB when it is clicked.
    //
    // This is adapted from Chris Banes' Stack Overflow answer: https://stackoverflow.com/a/41442923
    private fun hideAppBarFab(fab: FloatingActionButton) {
        //val params = fab.layoutParams as CoordinatorLayout.LayoutParams
        //val behavior = params.behavior as FloatingActionButton.Behavior
        //behavior.isAutoHideEnabled = false
        fab.hide()
    }

}

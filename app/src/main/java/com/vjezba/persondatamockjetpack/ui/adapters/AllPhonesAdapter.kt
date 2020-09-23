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

package com.vjezba.persondatamockjetpack.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vjezba.domain.model.AllPhones
import com.vjezba.persondatamockjetpack.databinding.ListItemRoomDisplayAllPhonesBinding

/**
 * Adapter for the [RecyclerView] in [PlantListFragment].
 */
class AllPhonesAdapter( val clickListener: (AllPhones) -> Unit) : ListAdapter<AllPhones, RecyclerView.ViewHolder>(PhonetDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LanguageViewHolder(
            ListItemRoomDisplayAllPhonesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val phone = getItem(position)
        (holder as LanguageViewHolder).bind(phone, clickListener)
    }

    class LanguageViewHolder(
        private val binding: ListItemRoomDisplayAllPhonesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AllPhones, clickListener: (AllPhones) -> Unit) {
            binding.apply {
                binding.ivDeletePhone.setOnClickListener {
                    clickListener( item )
                }
                phone = item
                executePendingBindings()
            }
        }
    }
}

private class PhonetDiffCallback : DiffUtil.ItemCallback<AllPhones>() {

    override fun areItemsTheSame(oldItem: AllPhones, newItem: AllPhones): Boolean {
        return oldItem.phoneId == newItem.phoneId
    }

    override fun areContentsTheSame(oldItem: AllPhones, newItem: AllPhones): Boolean {
        return oldItem == newItem
    }
}

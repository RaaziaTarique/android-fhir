/*
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.fhir.datacapture.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.fhir.datacapture.gallery.databinding.LandingPageItemBinding

class ComponentsRecyclerViewAdapter() :
  ListAdapter<ComponentsLayoutsViewModel.Components, SdcComponentsViewHolder>(
    ComponentsDiffUtil()
  ) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SdcComponentsViewHolder {
    return SdcComponentsViewHolder(
      LandingPageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
  }

  override fun onBindViewHolder(holder: SdcComponentsViewHolder, position: Int) {
    holder.bind(getItem(position))
  }
}

class SdcComponentsViewHolder(private val binding: LandingPageItemBinding) :
  RecyclerView.ViewHolder(binding.root) {
  fun bind(component: ComponentsLayoutsViewModel.Components) {
    binding.componentLayoutIconImageview.setImageResource(component.iconId)
    binding.componentLayoutTextView.text =
      binding.componentLayoutTextView.context.getString(component.textId)
  }
}

class ComponentsDiffUtil : DiffUtil.ItemCallback<ComponentsLayoutsViewModel.Components>() {
  override fun areItemsTheSame(
    oldComponent: ComponentsLayoutsViewModel.Components,
    newComponent: ComponentsLayoutsViewModel.Components
  ) = oldComponent == newComponent

  override fun areContentsTheSame(
    oldComponent: ComponentsLayoutsViewModel.Components,
    newComponent: ComponentsLayoutsViewModel.Components
  ) = areItemsTheSame(oldComponent, newComponent)
}
package com.valerasetrakov.simpleexample.adapters.event.event3

import com.valerasetrakov.simpleexample.R
import com.valerasetrakov.simpleexample.adapters.base.ProjectDelegate
import com.valerasetrakov.simpleexample.databinding.ItemEvent3Binding

class Event3Delegate: ProjectDelegate<Event3ViewModel, ItemEvent3Binding>(R.layout.item_event_3) {
    override fun checkType(item: Any): Boolean = item is Event3ViewModel
}
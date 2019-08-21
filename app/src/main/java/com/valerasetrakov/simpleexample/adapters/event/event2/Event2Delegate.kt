package com.valerasetrakov.simpleexample.adapters.event.event2

import com.valerasetrakov.simpleexample.R
import com.valerasetrakov.simpleexample.adapters.base.ProjectDelegate
import com.valerasetrakov.simpleexample.databinding.ItemEvent2Binding

class Event2Delegate: ProjectDelegate<Event2ViewModel, ItemEvent2Binding>(R.layout.item_event_2) {
    override fun checkType(item: Any): Boolean = item is Event2ViewModel
}
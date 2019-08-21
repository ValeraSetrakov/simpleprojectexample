package com.valerasetrakov.simpleexample.adapters.event.event1

import com.valerasetrakov.simpleexample.R
import com.valerasetrakov.simpleexample.adapters.base.ProjectDelegate
import com.valerasetrakov.simpleexample.databinding.ItemEvent1Binding

class Event1Delegate: ProjectDelegate<Event1ViewModel, ItemEvent1Binding>(R.layout.item_event_1) {
    override fun checkType(item: Any): Boolean = item is Event1ViewModel
}
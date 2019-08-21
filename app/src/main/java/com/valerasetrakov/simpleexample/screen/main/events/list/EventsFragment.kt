package com.valerasetrakov.simpleexample.screen.main.events.list

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.valerasetrakov.base.DelegatesAdapter
import com.valerasetrakov.simpleexample.R
import com.valerasetrakov.simpleexample.adapters.event.event1.Event1Delegate
import com.valerasetrakov.simpleexample.adapters.event.event2.Event2Delegate
import com.valerasetrakov.simpleexample.adapters.event.event3.Event3Delegate
import com.valerasetrakov.simpleexample.databinding.FragmentEventsBinding
import com.valerasetrakov.simpleexample.screen.main.MainActivityFragment

class EventsFragment: MainActivityFragment<EventsFragmentViewModel, FragmentEventsBinding>() {

    companion object {
        fun create() = EventsFragment()
    }

    override val toolbarTitleId: Int = R.string.event_schedule
    override val layoutId: Int = R.layout.fragment_events
    override val viewModelType: Class<EventsFragmentViewModel> = EventsFragmentViewModel::class.java

    private var offset = 0

    private val eventAdapter = DelegatesAdapter(listOf(
        Event1Delegate(),
        Event2Delegate(),
        Event3Delegate()
    ))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        offset = resources.getDimensionPixelSize(R.dimen.offset)
        viewmodel.requestEvents()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewEvents.apply {
            addItemDecoration(object: RecyclerView.ItemDecoration() {
                override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                    super.getItemOffsets(outRect, view, parent, state)
                    outRect.set(offset, offset, offset, offset)
                }
            })
            adapter = eventAdapter
        }
        viewmodel.events.observe(viewLifecycleOwner, Observer {
            eventAdapter.submitList(it)
        })
    }
}
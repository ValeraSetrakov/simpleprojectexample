package com.valerasetrakov.simpleexample.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.valerasetrakov.simpleexample.screen.main.MainActivityViewModel
import com.valerasetrakov.simpleexample.screen.main.events.list.EventsFragmentViewModel
import com.valerasetrakov.simpleexample.screen.main.profile.ProfileFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    internal abstract fun bindSplashActivityViewModel(viewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileFragmentViewModel::class)
    internal abstract fun bindProfileFragmentViewModel(viewModel: ProfileFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EventsFragmentViewModel::class)
    internal abstract fun bindEventsFragmentViewModel(viewModel: EventsFragmentViewModel): ViewModel

}
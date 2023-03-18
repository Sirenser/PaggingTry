package com.example.paggingtry.di

import com.example.paggingtry.MainActivity
import com.example.paggingtry.screens.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(mainFragment: MainFragment)

}
package com.example.snailpasswordmanager.di

import com.example.snailpasswordmanager.presentation.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class, RetrofitModule::class])
@Singleton
interface AppComponent {
    //fun inject(mainListActivity: RecordListFragment)
    //fun inject(mainActivity2: MainActivity2)
    //fun inject(registrationActivity: RegistrationActivity)
    //fun inject(accountInfoActivity: AccountInfoActivity)
    //fun inject(noteListFragment: NoteListFragment)
    //fun inject(noteActivity: NoteActivity)
    fun inject(mainActivity: MainActivity)
}
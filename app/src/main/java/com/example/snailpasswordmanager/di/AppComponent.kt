package com.example.snailpasswordmanager.di

import com.example.snailpasswordmanager.presentation.MainActivity
import com.example.snailpasswordmanager.presentation.accountInfo.AccountInfoActivity
import com.example.snailpasswordmanager.presentation.mainActivity.MainActivity2
import com.example.snailpasswordmanager.presentation.noteActivity.NoteActivity
import com.example.snailpasswordmanager.presentation.noteList.NoteListFragment
import com.example.snailpasswordmanager.presentation.recordList.RecordListFragment
import com.example.snailpasswordmanager.presentation.registration.RegistrationActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class, RetrofitModule::class])
@Singleton
interface AppComponent {
    fun inject(mainListActivity: RecordListFragment)
    fun inject(mainActivity2: MainActivity2)
    fun inject(registrationActivity: RegistrationActivity)
    fun inject(accountInfoActivity: AccountInfoActivity)
    fun inject(noteListFragment: NoteListFragment)
    fun inject(noteActivity: NoteActivity)
    fun inject(mainActivity: MainActivity)
}
package com.example.snailpasswordmanager.presentation.mainActivity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.snailpasswordmanager.LoginMode
import com.example.snailpasswordmanager.PasswordApp
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.databinding.ActivityMainBinding
import com.example.snailpasswordmanager.di.AppComponent
import com.example.snailpasswordmanager.presentation.noteList.NoteListViewModel
import com.example.snailpasswordmanager.presentation.noteList.NoteListViewModelFactory
import com.example.snailpasswordmanager.presentation.recordList.RecordListFragment
import com.google.android.material.navigation.NavigationView
import javax.inject.Inject

class MainActivity : AppCompatActivity(), AppComponentProvider {

    override val appComponent: AppComponent by lazy {
        (applicationContext as PasswordApp).appComponent
    }

    lateinit var vm : MainViewModel
    @Inject
    lateinit var vmFactory: MainViewModelFactory
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()

    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appComponent.inject(this)

        vm = ViewModelProvider(this, vmFactory)[MainViewModel::class.java]


        val navigationView : NavigationView = findViewById(R.id.navigationView)
        navigationView.itemIconTintList = null
        val navController = Navigation.findNavController(this,R.id.frameLayout)
        NavigationUI.setupWithNavController(navigationView,navController)

        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)

        vm.getAllData()

        val menuImage : ImageView = findViewById(R.id.imageMenu)

        menuImage.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

}//dddd@ddd.ddd
interface AppComponentProvider {
    val appComponent: AppComponent
}
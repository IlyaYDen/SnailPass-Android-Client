package com.example.snailpasswordmanager.presentation.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.snailpasswordmanager.PasswordApp
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.databinding.ActivityMainBinding
import com.example.snailpasswordmanager.di.AppComponent
import com.example.snailpasswordmanager.presentation.recordList.RecordListFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), AppComponentProvider {

    override val appComponent: AppComponent by lazy {
        (applicationContext as PasswordApp).appComponent
    }

    lateinit var bindingClass : ActivityMainBinding

    //private lateinit var vm : AccountInfoViewModel

    //@Inject
    //lateinit var vmFactory: AccountInfoModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        val navigationView : NavigationView = findViewById(R.id.navigationView)
        navigationView.itemIconTintList = null
        val navController = Navigation.findNavController(this,R.id.frameLayout)
        NavigationUI.setupWithNavController(navigationView,navController)

//
        //val fragmentTransition = supportFragmentManager.beginTransaction()
//
//
        //fragmentTransition.replace(R.id.frameLayout, RecordListFragment())
        //fragmentTransition.commit()


        val drawerLayout : DrawerLayout = bindingClass.drawerLayout
        val menuImage = bindingClass.imageMenu
        menuImage.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

}//dddd@ddd.ddd
interface AppComponentProvider {
    val appComponent: AppComponent
}
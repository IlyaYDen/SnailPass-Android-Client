package com.example.snailpasswordmanager.presentation.mainActivity

//import android.R
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.snailpasswordmanager.PasswordApp
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.di.AppComponent
import com.example.snailpasswordmanager.presentation.login.LoginActivity
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
        navigationView.menu.findItem(R.id.menuLogout).setOnMenuItemClickListener {
            val intent = Intent(this, LoginActivity::class.java).apply {

            }//todo remove cash
            finish()
            startActivity(intent)
            return@setOnMenuItemClickListener true
        }
/*
        navigationView.setNavigationItemSelectedListener {

            val id: Int = it.getItemId()
            if (id == R.id.menuLogout) {
                val intent = Intent(this, LoginActivity::class.java).apply {

                }

                finish()
                startActivity(intent)
            }
            return@setNavigationItemSelectedListener true
        }*/

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
package com.example.snailpasswordmanager.presentation


import android.content.Context
import android.opengl.GLSurfaceView
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.snailpasswordmanager.PasswordApp
import com.example.snailpasswordmanager.presentation.accountInfo.AccountInfoModelFactory
import com.example.snailpasswordmanager.presentation.accountInfo.AccountInfoScreen
import com.example.snailpasswordmanager.presentation.accountInfo.AccountInfoViewModel
import com.example.snailpasswordmanager.presentation.login.LoginModelFactory
import com.example.snailpasswordmanager.presentation.login.LoginViewModel
import com.example.snailpasswordmanager.presentation.login.components.LoginScreen
import com.example.snailpasswordmanager.presentation.recordList.RecordListScreen
import com.example.snailpasswordmanager.presentation.recordList.RecordListViewModel
import com.example.snailpasswordmanager.presentation.recordList.RecordListViewModelFactory
import com.example.snailpasswordmanager.presentation.registration.RegistrationModelFactory
import com.example.snailpasswordmanager.presentation.registration.RegistrationScreen
import com.example.snailpasswordmanager.presentation.registration.RegistrationViewModel
import com.example.snailpasswordmanager.presentation.theme.SnailPasswordManagerTheme
import com.example.snailpasswordmanager.utils.ApplicationUpdateUtility
import java.io.File
import java.util.*
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        ApplicationUpdateUtility.checkForUpdate(this)

        //context.getExternalFilesDir(null), name

        if (File(
                getExternalFilesDir(null),
                "app-release.apk"
            ).exists()
        ) {
            File(
                getExternalFilesDir(null),
                "app-release.apk"
            ).delete()
        }


        setContent {

            SnailPasswordManagerTheme {
                Navigation(this)
            }
        }
    }


    @Inject
    lateinit var loginVmFactory: LoginModelFactory
    @Inject
    lateinit var registrationVmFactory: RegistrationModelFactory
    @Inject
    lateinit var recordListVmFactory: RecordListViewModelFactory
    @Inject
    lateinit var recordInfoVmFactory: AccountInfoModelFactory
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun Navigation(context: Context) {
        (applicationContext as PasswordApp).appComponent.inject(this)

        val loginViewModel = ViewModelProvider(this, loginVmFactory)[LoginViewModel::class.java]
        val registrationViewModel = ViewModelProvider(this, registrationVmFactory)[RegistrationViewModel::class.java]
        val recordListViewModel = ViewModelProvider(this, recordListVmFactory)[RecordListViewModel::class.java]
        val recordInfoViewModel = ViewModelProvider(this, recordInfoVmFactory)[AccountInfoViewModel::class.java]


        val brush = Brush.horizontalGradient(listOf(Color.Red, Color.Blue))
        Canvas(
            modifier = Modifier.size(200.dp),
            onDraw = {
                drawCircle(brush)
            }
        )


        val navController = rememberNavController()
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        0f to Color(0xff202327),
                        1f to Color(0xff26292d),
                        start = Offset.Zero,
                        end = Offset.Infinite,
                        tileMode = TileMode.Clamp
                    )
                )
                //.drawWithCache {
                //    onDrawWithContent {
                //        //val b = ShaderBrush()
                //    }
                //}

                .fillMaxSize()
        )
        NavHost(navController = navController, startDestination = Screen.Login.route) {
            composable(
                route = Screen.Login.route
            ) {

                LoginScreen(
                    navController,
                    loginViewModel,
                    context
                )
            }
            composable(
                route = Screen.Registration.route + "?email={email}?password={password}",
                arguments = listOf(
                    navArgument("email") {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument("password") {
                        type = NavType.StringType
                        defaultValue = ""
                    }
                )
            ) {
                //LoginScreen(navController)
                RegistrationScreen(
                    navController = navController,
                    vm = registrationViewModel,
                    email = it.arguments?.getString("email"),
                    password = it.arguments?.getString("password")
                )
            }
            composable(
                route = Screen.RecordInfo.route + "?id={id}?service={service}?email={email}?password={password}?favorite={favorite}",
                arguments = listOf(

                    navArgument("id") {
                        type = NavType.StringType
                    },
                    navArgument("service") {
                        type = NavType.StringType
                    },
                    navArgument("email") {
                        type = NavType.StringType
                    },
                    navArgument("password") {
                        type = NavType.StringType
                    },
                    navArgument("favorite") {
                        type = NavType.StringType
                    }
                )
            ) {

                val id = it.arguments?.getString("id")
                if(id!=null) {
                    LaunchedEffect(navController.currentBackStackEntry) {
                        recordInfoViewModel.getAddFields(
                            UUID.fromString(
                                id
                            )
                        )
                    }
                    AccountInfoScreen(
                        navController = navController,
                        vm = recordInfoViewModel,
                        id = id,
                        name = it.arguments?.getString("service"),
                        email = it.arguments?.getString("email"),
                        password = it.arguments?.getString("password"),
                        favorite = it.arguments?.getString("favorite").toBoolean(),
                        new = false
                    )
                }
            }
            composable(
                route = Screen.RecordInfo.route
            ) {
                    recordInfoViewModel.clearList()
                    AccountInfoScreen(
                        navController = navController,
                        vm = recordInfoViewModel,
                        new = true
                    )
            }



            composable(
                route = Screen.RecordList.route,

            ){
                LaunchedEffect(navController.currentBackStackEntry) {
                    recordListViewModel.getPasswords()
                }
                //recordListViewModel.getPasswords()
                RecordListScreen(
                    navController,
                    recordListViewModel,
                    context
                )
            }
        }
    }
}

@Composable
fun OnLifecycleEvent(onEvent: (owner: LifecycleOwner, event: Lifecycle.Event) -> Unit) {
    val eventHandler = rememberUpdatedState(onEvent)
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    DisposableEffect(lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle
        val observer = LifecycleEventObserver { owner, event ->
            eventHandler.value(owner, event)
        }

        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}
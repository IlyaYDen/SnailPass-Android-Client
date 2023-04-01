package com.example.snailpasswordmanager.presentation


import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.core.content.PackageManagerCompat.LOG_TAG
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
import com.example.snailpasswordmanager.presentation.core.components.NavigationMenu
import com.example.snailpasswordmanager.presentation.core.components.NavigationSelected
import com.example.snailpasswordmanager.presentation.login.LoginModelFactory
import com.example.snailpasswordmanager.presentation.login.LoginViewModel
import com.example.snailpasswordmanager.presentation.login.components.LoginScreen
import com.example.snailpasswordmanager.presentation.noteActivity.NoteListScreen
import com.example.snailpasswordmanager.presentation.noteActivity.NoteScreen
import com.example.snailpasswordmanager.presentation.noteList.NoteListViewModel
import com.example.snailpasswordmanager.presentation.noteList.NoteListViewModelFactory
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
                "SnailPass.apk"
            ).exists()
        ) {
            File(
                getExternalFilesDir(null),
                "SnailPass.apk"
            ).delete()
        }


        setContent {

            SnailPasswordManagerTheme {
                Navigation(this)
            }
        }
    }


    @Inject
    lateinit var connectivityManager: ConnectivityManager
    @Inject
    lateinit var loginVmFactory: LoginModelFactory
    @Inject
    lateinit var registrationVmFactory: RegistrationModelFactory
    @Inject
    lateinit var recordListVmFactory: RecordListViewModelFactory
    @Inject
    lateinit var recordInfoVmFactory: AccountInfoModelFactory
    @Inject
    lateinit var NoteListVmFactory: NoteListViewModelFactory
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun  Navigation(context: Context) {
        (applicationContext as PasswordApp).appComponent.inject(this)

        val loginViewModel = ViewModelProvider(this, loginVmFactory)[LoginViewModel::class.java]
        val registrationViewModel = ViewModelProvider(this, registrationVmFactory)[RegistrationViewModel::class.java]
        val recordListViewModel = ViewModelProvider(this, recordListVmFactory)[RecordListViewModel::class.java]
        val recordInfoViewModel = ViewModelProvider(this, recordInfoVmFactory)[AccountInfoViewModel::class.java]
        val noteListViewModel = ViewModelProvider(this, NoteListVmFactory)[NoteListViewModel::class.java]

        val navMenu = remember {mutableStateOf(false)}
        val navigationSelected = remember {mutableStateOf(NavigationSelected.Accounts)}


        val animation by animateFloatAsState(
            if(navMenu.value) 0.8f else 0f
        )

        val brush = Brush.horizontalGradient(listOf(Color.Red, Color.Blue))
        Canvas(
            modifier = Modifier.size(200.dp),
            onDraw = {
                drawCircle(brush)
            }
        )


        val interactionSource1 = remember { MutableInteractionSource() }

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

                BackHandler(true) {
                    // Or do nothing
                }
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
                registrationViewModel.reset()
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
                        Log.d("test", "test times")
                        recordInfoViewModel.getAddFields(
                            UUID.fromString(
                                id
                            )
                        )
                    }
                    AccountInfoScreen(
                        navController = navController,
                        vm = recordInfoViewModel,
                        connectivityManager = connectivityManager,
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
                        new = true,
                        connectivityManager = connectivityManager
                    )
            }



            composable(
                route = Screen.RecordList.route,

            ){
                LaunchedEffect(navController.currentBackStackEntry) {
                    recordListViewModel.getPasswords()
                }
                //recordListViewModel.getPasswords()

                BackHandler(true) {
                    navigationSelected.value = NavigationSelected.Note
                    navController.navigate(Screen.NoteList.route)
                }
                RecordListScreen(
                    navController,
                    recordListViewModel,
                    context,
                    connectivityManager,
                    open = navMenu
                )

            }

            composable(
                route = Screen.NoteList.route,

                ){
                LaunchedEffect(navController.currentBackStackEntry) {
                    noteListViewModel.getNotes()
                }
                BackHandler(true) {
                    navigationSelected.value = NavigationSelected.Accounts
                    navController.navigate(Screen.RecordList .route)
                }
                NoteListScreen(
                    navController,
                    noteListViewModel,
                    context,
                    connectivityManager,
                    open = navMenu
                )
            }
            composable(
                route = Screen.NoteInfo.route + "?id={id}",
                arguments = listOf(

                    navArgument("id") {
                        type = NavType.StringType
                    },
                    //navArgument("name") {
                    //    type = NavType.StringType
                    //},
                    //navArgument("content") {
                    //    type = NavType.StringType
                    //},
                    //navArgument("favorite") {
                    //    type = NavType.StringType
                    //}
                )
            ){
                LaunchedEffect(navController.currentBackStackEntry) {
                }
                NoteScreen(
                    //nameString = it.arguments?.getString("name")?:"",
                    //valueString = it.arguments?.getString("content")?:"",
                    id = it.arguments?.getString("id"),
                    //favorite = it.arguments?.getString("favorite").toBoolean(),
                    vm = noteListViewModel,
                    nav = navController,
                    connectivityManager = connectivityManager,
                    new = false
                )
                //NoteListScreen(
                //    navController,
                //    noteListViewModel,
                //    context,
                //    connectivityManager
                //)
            }
            composable(
                route = Screen.NoteInfo.route
            ){
                NoteScreen(
                    new = true,
                    nav = navController,
                    vm = noteListViewModel,
                    connectivityManager = connectivityManager
                )
                //NoteListScreen(
                //    navController,
                //    noteListViewModel,
                //    context,
                //    connectivityManager
                //)
            }

        }

        if(animation!=0f)
            Box(
                modifier = Modifier.clickable(
                    interactionSource = interactionSource1,
                    indication = null,
                    enabled = true,
                    role = Role.Button
                )
                {
                    navMenu.value = false
                }
                    .fillMaxSize()
                    .background(
                        color = Color.Black.copy(alpha = animation*0.8f),
                    )
            )

        NavigationMenu(
            openSize = animation,
            navigationSelected = navigationSelected.value,
            notesButton = {
                navController.navigate(Screen.NoteList.route)
                navigationSelected.value = NavigationSelected.Note
                navMenu.value = false
            },
            homeButton = {
                //navController.navigate(Screen.Home.route) todo
                //navigationSelected.value = NavigationSelected.Home
                navMenu.value = false
            },
            accountsButton = {

                navController.navigate(Screen.RecordList.route)
                navigationSelected.value = NavigationSelected.Accounts
                navMenu.value = false
            },
            logoutButton = {
                navController.navigate(Screen.Login.route)
                navigationSelected.value = NavigationSelected.Accounts
                navMenu.value = false
            }
        )
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
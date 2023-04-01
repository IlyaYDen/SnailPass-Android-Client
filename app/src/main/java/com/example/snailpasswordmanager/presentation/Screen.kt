package com.example.snailpasswordmanager.presentation

sealed class Screen(val route: String) {
    object Login : Screen(route = "login_screen")
    object Registration : Screen(route = "registration_screen")
    object RecordList : Screen(route = "record_list")
    object RecordInfo : Screen(route = "record_info")

    object NoteList : Screen(route = "note_list")
    object NoteInfo : Screen(route = "note_info")

    fun withArgs(vararg args: Pair<String,String>) =
        buildString {
            append(route)
            args.forEach { arg ->
                append("?${arg.first}=${arg.second}")
            }
        }
}
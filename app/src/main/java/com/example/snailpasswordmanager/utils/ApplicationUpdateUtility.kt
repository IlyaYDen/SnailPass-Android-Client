package com.example.snailpasswordmanager.utils

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.app.DownloadManager
import android.content.*
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.view.Window
import androidx.core.content.FileProvider
import com.example.snailpasswordmanager.BuildConfig
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.presentation.login.LoginActivity
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.io.IOException


object ApplicationUpdateUtility {

    private val TAG = "release"

    fun checkForUpdate(loginActivity: LoginActivity) {
        val latestReleaseUrl =
            "https://api.github.com/repos/IlyaYDen/SnailPass-Android-Client/releases/latest"

        // Make a network request to get the latest release information
        val request = Request.Builder()
            .url(latestReleaseUrl)
            .build()

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .build()

        var t: Long = 0

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle failure
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    // Handle failure
                    return
                }

                val jsonString = response.body?.string()
                val latestRelease = Gson().fromJson(jsonString, LatestRelease::class.java)

                Log.d(
                    TAG,
                    "Latest release info: $latestRelease " + compareVersions(
                        latestRelease.tagName,
                        BuildConfig.VERSION_NAME
                    )
                )

                if (compareVersions(latestRelease.tagName, BuildConfig.VERSION_NAME) > 0) {

                    /*
                    setTitle("Важное сообщение!")
                            .setMessage("Покормите кота!")
                            .setIcon(R.drawable.account_icon)
                     */
                    loginActivity.runOnUiThread {

                        val builder = AlertDialog.Builder(loginActivity)
                        builder
                            .setTitle(R.string.update_title)
                            .setMessage(R.string.update_message)

                            .setIcon(R.drawable.account_icon)
                            .setPositiveButton(R.string.download) { dialog, id ->
                                downloadUpdate(
                                    loginActivity,
                                    latestRelease
                                )
                            }
                            .setNegativeButton(R.string.cancel) { dialog, id ->





                                if (File(
                                        loginActivity.getExternalFilesDir(null),
                                        "app-release.apk"
                                    ).exists()
                                ) {
                                    File(
                                        loginActivity.getExternalFilesDir(null),
                                        "app-release.apk"
                                    ).delete()
                                }

                                dialog.cancel()
                            }
                        builder.create()
                            .show()
                        /*
                        AlertDialog.Builder(loginActivity)

                            .setTitle(R.string.update_title)
                            .setMessage(R.string.update_message) // Specifying a listener allows you to take an action before dismissing the dialog.
                            .setIcon(R.drawable.account_icon)
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setPositiveButton(
                                android.R.string.yes,
                                DialogInterface.OnClickListener { _, _ ->
                                    downloadUpdate(
                                        loginActivity,
                                        latestRelease
                                    )
                                }) // A null listener allows the button to dismiss the dialog and take no further action.
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show()*/
                    }


                }
            }
        })
    }

    private fun downloadUpdate(
        context: Context,
        latestRelease: LatestRelease
    ) {


        // A new release is available, download and install it
        var url = ""
        var name = ""
        for (i in latestRelease.assets) {
            if (i.name.endsWith(".apk")) {
                url = i.browser_download_url
                name = i.name
            }
        }
        if (File(
                context.getExternalFilesDir(null),
                name
            ).exists()
        ) {
            File(
                context.getExternalFilesDir(null),
                name
            ).delete()
        }

        val request = DownloadManager.Request(Uri.parse(url))
            .setDestinationInExternalFilesDir(context,null, name)
            //.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name)
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        val downloadId = downloadManager.enqueue(request)


// Register a broadcast receiver to receive the download complete event
        val onComplete = object : BroadcastReceiver() {
            @SuppressLint("Range")
            override fun onReceive(context: Context, intent: Intent) {
                if (intent.action == DownloadManager.ACTION_DOWNLOAD_COMPLETE) {
                    val query = DownloadManager.Query()
                    query.setFilterById(downloadId)
                    val cursor = downloadManager.query(query)
                    if (cursor.moveToFirst()) {
                        val status =
                            cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                        if (status == DownloadManager.STATUS_SUCCESSFUL) {
                            val downloadedFile = File(context.getExternalFilesDir(null), name)

                            val apkUri = FileProvider.getUriForFile(
                                context,
                                BuildConfig.APPLICATION_ID + ".provider",
                                downloadedFile
                            )
                            val installIntent = Intent(Intent.ACTION_VIEW)
                            installIntent.setDataAndType(
                                apkUri,
                                "application/vnd.android.package-archive"
                            )
                            installIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            context.startActivity(installIntent)
                        }
                    }
                    cursor.close()
                }
            }
        }

        context.registerReceiver(onComplete, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }

    fun compareVersions(v1: String, v2: String): Int {
        val v1Parts = v1.split('.')
        val v2Parts = v2.split('.')

        for (i in 0 until maxOf(v1Parts.size, v2Parts.size)) {
            val v1Part = if (i < v1Parts.size) v1Parts[i].filter { it.isDigit() }.toInt() else 0
            val v2Part = if (i < v2Parts.size) v2Parts[i].filter { it.isDigit() }.toInt() else 0

            if (v1Part > v2Part) {
                return 1
            } else if (v1Part < v2Part) {
                return -1
            }
        }

        return 0
    }
}
    data class LatestRelease(
    @SerializedName("tag_name")
    val tagName: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("body")
    val description: String,
    @SerializedName("html_url")
    val releaseUrl: String,
    @SerializedName("assets")
    val assets: List<ReleaseAsset>
) {
    data class ReleaseAsset (
        val name : String,
        val browser_download_url:String
            )
}



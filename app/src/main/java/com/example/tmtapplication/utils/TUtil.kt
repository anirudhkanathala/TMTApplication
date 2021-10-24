package com.example.tmtapplication.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import com.example.tmtapplication.BuildConfig
import com.google.android.material.snackbar.Snackbar
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


object TUtil {

    val BUILD_PROD = "release"
    /**
     * This method is used to check the internet connectivity.
     * @param context Android Context
     * @return Boolean This returns boolean to determine the device connectivity status.
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun isNetworkConnectionAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    TLog.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    TLog.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    TLog.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> {
                    TLog.i("Internet", "NetworkCapabilities.TRANSPORT_VPN")
                    return true
                }
            }
        }
        return false
    }


    /**
     * This method is used to determine the build variant.
     * @return String -  This returns build variant string ( debug | release)
     */
    fun isNonProd() = BuildConfig.BUILD_TYPE != BUILD_PROD

    /**
     * This method is used to show the generic snack bar from the bottom.
     * @param root - Root view of the current visible layout.
     * @param snackTitle - Title that needs to shown.
     * @return String -  This returns build variant string ( debug | release)
     */
    fun setSnackBar(root: View, snackTitle: String) {
        Snackbar.make(root, snackTitle, Snackbar.LENGTH_LONG).show()
    }

    /**
     * This method is used to get the specific error per exception.
     * @param e - Throwable
     * @return String -  Appropriate error according the exception
     */
    fun handleException(e: Throwable): String {
        return when (e) {
            is HttpException -> "Something went wrong, Please try later"
            is SocketTimeoutException -> "Something went wrong, Please try later"
            is UnknownHostException -> "Something went wrong, Please check your internet connection"
            else -> "Something went wrong, Please try later"
        }
    }

    /**
     * This method is used to hide the keyboard.
     * @param activity - Provide activity
     * @return Nothing
     */
    fun hideKeyboard(activity: FragmentActivity?) {
        val view = activity?.findViewById<View>(android.R.id.content)
        if (view != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}

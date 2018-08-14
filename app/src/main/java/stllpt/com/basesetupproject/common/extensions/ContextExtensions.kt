package stllpt.com.basesetupproject.common.extensions

import android.app.Activity
import android.content.*
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.Uri
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import org.jetbrains.anko.internals.AnkoInternals
import stllpt.com.basesetupproject.AppApplication
import stllpt.com.basesetupproject.BuildConfig
import stllpt.com.basesetupproject.R

/**
 * Created by stllpt065 on 6/7/17.
 */
/**
 * Toast to display String as Charsequence
 */
fun Context.charToast(char: CharSequence) = Toast.makeText(this, char, Toast.LENGTH_LONG).show()

/**
 * Toast to display String from Resource file as int
 */
fun Context.resToast(res: Int) = Toast.makeText(this, res, Toast.LENGTH_SHORT).show()

fun Context.longCharToast(char: CharSequence) = Toast.makeText(this, char, Toast.LENGTH_LONG).show()

inline fun <reified T : Activity> Activity.startActivityIfRunning(vararg params: Pair<String, Any>) {

    if (!isDestroyed) {
        AnkoInternals.internalStartActivity(this, T::class.java, params)
    }
}

inline fun <reified T : Activity> Activity.startActivityWithNoAnimation(vararg params: Pair<String, Any>) {
    AnkoInternals.internalStartActivity(this, T::class.java, params)
}


fun Activity.resToast(@StringRes res: Int) {
    val li = this.layoutInflater
    val toast = Toast(this)
    toast.apply {
        duration = Toast.LENGTH_SHORT
        setText(res)
        show()
    }
}

fun Activity.resToast(message: String) {
    val li = this.layoutInflater
    val toast = Toast(this)
    toast.apply {
        duration = Toast.LENGTH_SHORT
        setText(message)
        show()
    }
}

fun Context.hideKeyboard(view: View?) {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view?.windowToken, 0)
    imm.showSoftInput(view, 0)
}

fun Context.showKeyBoard(view: View?) {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

fun Context.getPrefInstance(prefName: String): SharedPreferences =
        this.getSharedPreferences(prefName, android.content.Context.MODE_PRIVATE)

fun log(msg: Any?, tag: String? = null) {
    val tagName = tag?.let {
        it
    } ?: AppApplication.getAppContext().applicationContext.applicationInfo.name + "Logs"

    if (BuildConfig.DEBUG) {
        Log.d(tagName, "$msg")
    }
}

fun Throwable.printDebugStackTrace() {
    if (BuildConfig.DEBUG) {
        this.printStackTrace()
    }
}

fun Context?.isInternetAvailable(): Boolean {
    return try {
        val connectivityManager = this?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo != null &&
                (connectivityManager.activeNetworkInfo.isConnected)
    } catch (ex: Exception) {
        ex.printDebugStackTrace()
        false
    }
}

fun Context.getCompatDrawable(@DrawableRes id: Int): Drawable? =
        ContextCompat.getDrawable(this, id)

fun Context.getCompatColor(@ColorRes id: Int): Int =
        ContextCompat.getColor(this, id)

fun Activity.requestRequiredPermission(permissions: Array<String>, requestCode: Int) {
    ActivityCompat.requestPermissions(this, permissions, requestCode)
}

fun Fragment.requestPermissionFromFragment(permissions: Array<String>, requestCode: Int) {
    this.requestPermissions(permissions, requestCode)
}

fun Context.checkRequiredPermission(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

fun Context.copyToClipBoard(textToCopy: String) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText(getString(R.string.app_name), textToCopy)
    clipboard.primaryClip = clip
}

fun Activity.openInstagramPage(page: String) {
    val uri = Uri.parse("http://instagram.com/_u/$page")
    val likeIng = Intent(Intent.ACTION_VIEW, uri)
    likeIng.`package` = "com.instagram.android"
    try {
        startActivity(likeIng)
    } catch (e: ActivityNotFoundException) {
        startActivity(Intent(Intent.ACTION_VIEW,
                Uri.parse("http://instagram.com/$page")))
    }
}

fun Context.email(email: Array<String>, subject: String = "", text: String = ""): Boolean {
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.type = "message/rfc822"
    intent.data = Uri.parse("mailto:")
    intent.putExtra(Intent.EXTRA_EMAIL, email)
    if (subject.isNotEmpty())
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
    if (text.isNotEmpty())
        intent.putExtra(Intent.EXTRA_TEXT, text)
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
        return true
    }
    return false

}

fun Activity.openSnapchatPage(page: String) {
    val uri = Uri.parse("snapchat://add/$page")
    val likeIng = Intent(Intent.ACTION_VIEW, uri)
    likeIng.`package` = "com.instagram.android"
    try {
        startActivity(likeIng)
    } catch (e: ActivityNotFoundException) {
        startActivity(Intent(Intent.ACTION_VIEW,
                Uri.parse("https://snapchat.com/add/$page")))
    }
}



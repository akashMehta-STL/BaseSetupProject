package stllpt.com.basesetupproject.common.extensions

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * To store integer type of value in shared preferences
 * @param def default value
 * @param key key of particular value
 * */
fun SharedPreferences.prefInt(def: Int = 0, key: String? = null) =
        preferenceManager(def, key, SharedPreferences::getInt, SharedPreferences.Editor::putInt)

/**
 * To store long type of value in shared preferences
 * @param def default value
 * @param key key of particular value
 * */
fun SharedPreferences.prefLong(def: Long = 0, key: String? = null) =
        preferenceManager(def, key, SharedPreferences::getLong, SharedPreferences.Editor::putLong)

/**
 * To store String type of value in shared preferences
 * @param def default value
 * @param key key of particular value
 * */
fun SharedPreferences.prefString(def: String = "", key: String? = null) =
        preferenceManager(def, key, SharedPreferences::getString, SharedPreferences.Editor::putString)

/**
 * To store Boolean type of value in shared preferences
 * @param def default value
 * @param key key of particular value
 * */
fun SharedPreferences.prefBoolean(def: Boolean = true, key: String? = null) =
        preferenceManager(def, key, SharedPreferences::getBoolean, SharedPreferences.Editor::putBoolean)

/**
 * To store Float type of value in shared preferences
 * @param def default value
 * @param key key of particular value
 * */
fun SharedPreferences.prefFloat(def: Float = 0F, key: String? = null) =
        preferenceManager(def, key, SharedPreferences::getFloat, SharedPreferences.Editor::putFloat)

/**
 * Generic extension function to actually store and get value in preferences.
 * @param defaultValue default value of generic type
 * @param key key for value
 * @param getter extension function for getting value from shared preferences
 * @param setter extension function for setting value into shared preferences
 * */
private inline fun <T> SharedPreferences.preferenceManager(
        defaultValue: T,
        key: String?,
        crossinline getter: SharedPreferences.(String, T) -> T,
        crossinline setter: SharedPreferences.Editor.(String, T) -> SharedPreferences.Editor
): ReadWriteProperty<Any, T> {
    return object : ReadWriteProperty<Any, T> {
        override fun getValue(thisRef: Any, property: KProperty<*>) =
                getter(key ?: property.name, defaultValue)

        override fun setValue(thisRef: Any, property: KProperty<*>,
                              value: T) =
                edit().setter(key ?: property.name, value).apply()
    }
}
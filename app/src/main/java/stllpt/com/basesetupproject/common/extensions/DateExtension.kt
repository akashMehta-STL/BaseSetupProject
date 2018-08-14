package stllpt.com.basesetupproject.common.extensions

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by aakash on 7/2/18.
 */
// Server date formats
val SERVER_DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
val SERVER_DATE_FORMAT_WT_MS = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

// Local date formats
val LOCAL_DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
val DISPLAY_FORMAT = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
val EXPIRING_DATE_FORMAT = SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault())
val SPEC_REWARD_EXPRIRES = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
val EXPIRES_ON = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())
val ACTIVITY_DATE_FORMAT = SimpleDateFormat("EEEE, MMMM dd, yyyy | hh:mm a", Locale.getDefault())

// Individual formats
val YEAR_FORMAT = SimpleDateFormat("yyyy", Locale.getDefault())
val MONTH_FORMAT = SimpleDateFormat("MM", Locale.getDefault())
val DAY_FORMAT = SimpleDateFormat("dd", Locale.getDefault())
val DAY_STRING_FORMAT = SimpleDateFormat("EEE", Locale.getDefault())
val TIME_FORMAT = SimpleDateFormat("hh:mm a", Locale.getDefault())

@Throws(Exception::class)
fun changeToServerFormat(year: Int, month: Int, day: Int): String =
        LOCAL_DATE_FORMAT.parse("$year-$month-$day").changeToServerDate()

@Throws(Exception::class)
fun Date.getLocalYear(): Int = YEAR_FORMAT.format(this).toInt()

@Throws(Exception::class)
fun Date.getLocalMonth(): Int = MONTH_FORMAT.format(this).toInt()

@Throws(Exception::class)
fun Date.getLocalDay(): Int = DAY_FORMAT.format(this).toInt()

@Throws(Exception::class)
fun Date.getLocalDayString(): String = DAY_STRING_FORMAT.format(this)

@Throws(Exception::class)
fun Date.changeToServerDate() = SERVER_DATE_FORMAT.format(this.updateMonth(reduce = false))

fun String.changeToBdayFormat() = try {
    DISPLAY_FORMAT.format(SERVER_DATE_FORMAT.parse(this))
} catch (ex: Exception) {
    ""
}
fun String.changeMSToBdayFormat() = try {
    DISPLAY_FORMAT.format(SERVER_DATE_FORMAT_WT_MS.parse(this))
} catch (ex: Exception) {
    ""
}

@Throws(Exception::class)
fun String.changeToTime() = TIME_FORMAT.format(SERVER_DATE_FORMAT.parse(this))


@Throws(Exception::class)
fun String.changeToMsTime(): String {
    SERVER_DATE_FORMAT_WT_MS.timeZone = TimeZone.getTimeZone("UTC")
    return TIME_FORMAT.format(SERVER_DATE_FORMAT_WT_MS.parse(this))
}


fun Date.updateMonth(reduce: Boolean): Date {
    val cal = Calendar.getInstance()
    cal.time = this
    cal.add(Calendar.MONTH, if (reduce) -1 else 1)
    return cal.time
}

fun Long.wasBeforeSixHours() =
        ((Date().time - this) >= 6 * 3600 * 1000L)

fun Long.wasPreviousDay(): Boolean {
    val lastSyncDate = Date(this)
    val today = Date()
    return ((today.time - this) >= 24 * 3600 * 1000L) ||
            today.getLocalDay() > lastSyncDate.getLocalDay()
}

@Throws(Exception::class)
fun getCurrentDay(): String = Date().getLocalDayString()

fun String.toExpiringDate(): String {
    SERVER_DATE_FORMAT_WT_MS.timeZone = TimeZone.getTimeZone("UTC")
    return EXPIRING_DATE_FORMAT.format(SERVER_DATE_FORMAT_WT_MS.parse(this))
}

fun String.toExpiresOn(): String {
    SERVER_DATE_FORMAT_WT_MS.timeZone = TimeZone.getTimeZone("UTC")
    return EXPIRES_ON.format(SERVER_DATE_FORMAT_WT_MS.parse(this))
}

fun String.specRewardExpires(): String {
    SERVER_DATE_FORMAT_WT_MS.timeZone = TimeZone.getTimeZone("UTC")
    return SPEC_REWARD_EXPRIRES.format(SERVER_DATE_FORMAT_WT_MS.parse(this))
}

fun String.expiringTime(): Triple<Long, Long, Long> {
    val curDate = Calendar.getInstance()
    val endDate = Calendar.getInstance()
    SERVER_DATE_FORMAT_WT_MS.timeZone = TimeZone.getTimeZone("UTC")
    endDate.time = SERVER_DATE_FORMAT_WT_MS.parse(this)
    val diffInSecs = (endDate.timeInMillis - curDate.timeInMillis) / 1000

    return Triple(remainingDays(diffInSecs), remainingHrs(diffInSecs), remainingMins(diffInSecs))
}

@Throws(Exception::class)
fun String.toActivityDateFormat() = ACTIVITY_DATE_FORMAT.format(SERVER_DATE_FORMAT_WT_MS.parse(this))

fun String.differenceFromToday(): Triple<Long, Long, Long> {
    val curDate = Calendar.getInstance()
    val endDate = Calendar.getInstance()
    SERVER_DATE_FORMAT_WT_MS.timeZone = TimeZone.getTimeZone("UTC")
    endDate.time = SERVER_DATE_FORMAT_WT_MS.parse(this)
    val diffInSecs = (curDate.timeInMillis - endDate.timeInMillis) / 1000

    return Triple(remainingDays(diffInSecs), remainingHrs(diffInSecs), remainingMins(diffInSecs))
}

fun String.getNotificationTime() : String {
    val date = this.differenceFromToday()
    if (date.first > 0) {
        return "${date.first} days"
    } else if (date.second > 0) {
        return "${date.second} hours"
    } else if (date.third > 0) {
        return "${date.third} mins"
    }
    return "Just Now"
}

fun remainingDays(sec: Long): Long = if (sec > 0) sec / 86400 else 0

fun remainingHrs(sec: Long): Long = if (sec > 0) (sec % 86400) / 3600 else 0

fun remainingMins(sec: Long): Long = if (sec > 0) (sec % 3600) / 60 else 0


fun String.getLocalDate(): Date {
    val cal = Calendar.getInstance()
    val date = SERVER_DATE_FORMAT_WT_MS.parse(this)
    cal.time = date
    cal.set(2000, 1, 1)
    return cal.time
}

fun String.convertDOB(): String {
    return DISPLAY_FORMAT.format(LOCAL_DATE_FORMAT.parse(this))
}
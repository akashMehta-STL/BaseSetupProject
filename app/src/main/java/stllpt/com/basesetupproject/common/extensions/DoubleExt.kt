package stllpt.com.basesetupproject.common.extensions

/**
 * Created by solutelabs on 30/3/18.
 */

fun Double?.toDistanceString(): String {
    this?.let {
        return if (this > 999) {
            String.format("%.1f", this.div(1000).toFloat()) + " km"
        } else {
            String.format("%.1f", this.toFloat()) + " m"
        }
    }
    return "-"
}
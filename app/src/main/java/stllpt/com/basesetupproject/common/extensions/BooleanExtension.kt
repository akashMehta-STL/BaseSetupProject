package stllpt.com.basesetupproject.common.extensions

import android.view.View

/**
 * Created by stllpt031 on 16/2/18.
 */
fun Boolean.isVisible() = if (this) View.VISIBLE else View.GONE
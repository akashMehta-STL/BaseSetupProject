package stllpt.com.basesetupproject.shareddata.base

import android.content.Context
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by stllpt031 on 23/8/18.
 */
interface BaseView {
    val mContext: Context?
    val compositeDisposable: CompositeDisposable
}
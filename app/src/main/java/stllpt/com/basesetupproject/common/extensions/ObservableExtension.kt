package stllpt.com.basesetupproject.common.extensions

import io.reactivex.Observable

/**
 * Created by stllpt031 on 28/8/18.
 */
fun <T> Observable<T>.log(tag: String): Observable<T> = doOnNext { stllpt.com.basesetupproject.common.extensions.log("$tag: $it") }

package stllpt.com.basesetupproject.ui.users.components

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import stllpt.com.basesetupproject.common.extensions.log
import stllpt.com.basesetupproject.ui.users.MainEndPoint
import stllpt.com.basesetupproject.ui.users.common.intention

/**
 * Created by aakash on 26/8/18.
 */
data class MainUiEvents(
        val snackbarShown: PublishSubject<Unit> = PublishSubject.create(),
        val loadScreen: PublishSubject<Unit> = PublishSubject.create()
)

sealed class MainActions {
    object SnackbarShown : MainActions()
    object LoadScreen : MainActions()
}

fun main(sources: MainUiEvents, mainEndPoint: MainEndPoint): Observable<MainState> {
    val intention = intention(sources)
    log(" Main : intention : $intention")
    return intention.log("UIaction : source : $sources, mainEndPoint : $mainEndPoint")
            .publish {
                model(it, mainEndPoint)
                        .map { it }
            }
            .share()
}
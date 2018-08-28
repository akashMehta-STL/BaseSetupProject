package stllpt.com.basesetupproject.ui.users.components

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import stllpt.com.basesetupproject.common.extensions.log
import stllpt.com.basesetupproject.ui.users.MainPresenter
import stllpt.com.basesetupproject.ui.users.common.intention

/**
 * Created by aakash on 26/8/18.
 */
data class MainUiEvents(
        val snackbarShown: PublishRelay<Unit> = PublishRelay.create(),
        val loadScreen: PublishRelay<Unit> = PublishRelay.create()
)

sealed class MainActions {
    object SnackbarShown : MainActions()
    object LoadScreen : MainActions()
}

fun main(sources: MainUiEvents, mainPresenter: MainPresenter): Observable<MainState> = intention(sources)
        .log("action")
        .publish {
            val state = model(it, mainPresenter)
                    .map { it }

            state
        }
        .share()
package stllpt.com.basesetupproject.ui.users.components

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import stllpt.com.basesetupproject.ui.users.common.intention

/**
 * Created by aakash on 26/8/18.
 */
data class MainUiEvents(
        val snackbarShown: PublishRelay<Unit> = PublishRelay.create()
)

data class MainSources(
        val uiEvents: MainUiEvents
)

sealed class MainActions {
    object SnackbarShown : MainActions()
    object LoadScreen : MainActions()
}

sealed class MainSink {
    data class State(val state : MainState) : MainSink()
    data class State2(val state : MainState) : MainSink()
}

fun main(sources: MainSources) : Observable<MainSink> = intention(sources)
        .publish {
            val state = model(it)
                    .map { MainSink.State(it) }
            val state2 = model(it)
                    .map { MainSink.State2(it) }

            Observable.merge(state, state2)
        }
        .share()
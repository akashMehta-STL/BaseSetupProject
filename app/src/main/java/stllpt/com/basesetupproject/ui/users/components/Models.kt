package stllpt.com.basesetupproject.ui.users.components

import io.reactivex.Observable
import stllpt.com.basesetupproject.common.helper.SnackbarMessage
import stllpt.com.basesetupproject.ui.users.model.ItemsItem

/**
 * Created by aakash on 26/8/18.
 */
data class MainState(
        val loading: Boolean = false,
        val handleInternetError: Unit = Unit,
        val details: List<ItemsItem> = emptyList(),
        val snackBar: SnackbarMessage = SnackbarMessage(show = false)
)

typealias MainStateReducer = (MainState) -> MainState


fun model(action: Observable<MainActions>): Observable<MainState> {
    val snacbarShown = action
            .ofType(MainActions.SnackbarShown::class.java)
            .map { snackbarReducer() }
    val initialState = MainState()
    val reducer = listOf(snacbarShown)
    return Observable.merge(reducer)
            .scan(initialState, { state, reducer -> reducer(state) })
            .skip(1)
            .distinctUntilChanged()
}

fun snackbarReducer(): MainStateReducer = {
    it.copy(snackBar = it.snackBar.copy(show = false))
}
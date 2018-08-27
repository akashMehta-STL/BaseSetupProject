package stllpt.com.basesetupproject.ui.users.components

import io.reactivex.Observable
import stllpt.com.basesetupproject.common.helper.SnackbarMessage
import stllpt.com.basesetupproject.ui.users.GetApiResponse
import stllpt.com.basesetupproject.ui.users.MainPresenter
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


fun model(action: Observable<MainActions>,
          endPoints: MainPresenter): Observable<MainState> {
    val snacbarShown = action
            .ofType(MainActions.SnackbarShown::class.java)
            .map { snackbarReducer() }
    val loadScreen = action
            .ofType(MainActions.LoadScreen::class.java)
            .switchMap {
                endPoints.fetchUserList()
                        .map(::loadScreenReducer)
                        .startWith(loadItemsReducer())
            }

    val initialState = MainState()
    val reducers = listOf(snacbarShown, loadScreen)
    return Observable.merge(reducers)
            .scan(initialState) { state, reducer -> reducer(state) }
            .skip(1)
            .distinctUntilChanged()
}

fun loadItemsReducer(): MainStateReducer = {
    it.copy(loading = true)
}

fun snackbarReducer(): MainStateReducer = {
    it.copy(snackBar = it.snackBar.copy(show = false))
}

fun loadScreenReducer(result: GetApiResponse): MainStateReducer = {
    when (result) {
        is GetApiResponse.Success -> {
            it.copy(
                    loading = false,
                    handleInternetError = Unit,
                    details = result.gitUserResponse.items ?: emptyList(),
                    snackBar = it.snackBar.copy(show = false)
            )
        }
        is GetApiResponse.WrongResponseError -> {
            it.copy(
                    loading = false,
                    handleInternetError = Unit,
                    details = emptyList(),
                    snackBar = it.snackBar.copy(show = true, message = "Api response error occured.")
            )
        }

        is GetApiResponse.NullResponseError -> {
            it.copy(
                    loading = false,
                    handleInternetError = Unit,
                    details = emptyList(),
                    snackBar = it.snackBar.copy(show = true, message = "Null api response.")
            )
        }
    }
}
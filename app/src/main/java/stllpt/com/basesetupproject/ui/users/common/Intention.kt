package stllpt.com.basesetupproject.ui.users.common

import io.reactivex.Observable
import stllpt.com.basesetupproject.ui.users.components.MainActions
import stllpt.com.basesetupproject.ui.users.components.MainUiEvents

/**
 * Created by aakash on 26/8/18.
 */
fun intention(sources: MainUiEvents) : Observable<MainActions>{
    val snackbarShown = sources.snackbarShown
            .map { MainActions.SnackbarShown }
    val loadScreen = sources.loadScreen
            .map { MainActions.LoadScreen }
    val action = listOf(snackbarShown, loadScreen)
    return Observable.merge(action)
}
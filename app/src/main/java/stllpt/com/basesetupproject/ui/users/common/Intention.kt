package stllpt.com.basesetupproject.ui.users.common

import io.reactivex.Observable
import stllpt.com.basesetupproject.ui.users.components.MainActions
import stllpt.com.basesetupproject.ui.users.components.MainSources

/**
 * Created by aakash on 26/8/18.
 */
fun intention(sources: MainSources) : Observable<MainActions>{
    val snackbarShown = sources.uiEvents.snackbarShown
            .map { MainActions.SnackbarShown }
    val loadScreen = sources.uiEvents.loadScreen
            .map { MainActions.LoadScreen }
    val action = listOf(snackbarShown, loadScreen)
    return Observable.merge(action)
}
package stllpt.com.basesetupproject.ui.users.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.ViewModel
import io.reactivex.BackpressureStrategy
import stllpt.com.basesetupproject.common.extensions.log
import stllpt.com.basesetupproject.ui.users.MainEndPoint
import stllpt.com.basesetupproject.ui.users.components.*

/**
 * Created by aakash on 26/8/18.
 */
class MainViewModel(mEndPoint: MainEndPoint) : ViewModel() {
    val state: LiveData<MainState>
    val uiEvents = MainUiEvents()

    init {
        val mainSources = uiEvents
        log("UIEvents : $mainSources")
        val sinks = main(mainSources, mEndPoint)
        state = LiveDataReactiveStreams.fromPublisher(
                sinks.toFlowable(BackpressureStrategy.LATEST)
        )
    }
}
package stllpt.com.basesetupproject.ui.users.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.ViewModel
import io.reactivex.BackpressureStrategy
import stllpt.com.basesetupproject.ui.users.components.*

/**
 * Created by aakash on 26/8/18.
 */
class MainViewModel : ViewModel() {
    val state: LiveData<MainState>
    val uiEvents = MainUiEvents()

    init {
        val mainSources = MainSources(uiEvents)
val sinks = main(mainSources)
        state = LiveDataReactiveStreams.fromPublisher(sinks
                .ofType(MainSink.State::class.java)
                .map { it.state }
                .toFlowable(BackpressureStrategy.LATEST)
        )
    }
}
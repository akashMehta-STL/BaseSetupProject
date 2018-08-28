package stllpt.com.basesetupproject.ui.users.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import stllpt.com.basesetupproject.common.extensions.log
import stllpt.com.basesetupproject.ui.users.MainEndPoint

/**
 * Created by aakash on 26/8/18.
 */
class MainViewModelFactory(val mainEndPoint: MainEndPoint) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        log("ViewModelFactory : main end point : $mainEndPoint")
        return MainViewModel(mainEndPoint) as T
    }
}
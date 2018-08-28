package stllpt.com.basesetupproject.ui.users.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import stllpt.com.basesetupproject.ui.users.MainPresenter

/**
 * Created by aakash on 26/8/18.
 */
class MainViewModelFactory(val mainPresenter: MainPresenter) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(mainPresenter) as T
    }
}
package stllpt.com.basesetupproject.ui.users.view

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import stllpt.com.basesetupproject.ui.users.model.UserStates
import stllpt.com.basesetupproject.ui.users.repo.UserRepo
import javax.inject.Inject

/**
 * Created by stllpt031 on 28/8/18.
 */
class UserViewModel  @Inject constructor(private val mUserRepo: UserRepo) : ViewModel() {
    private var userModel = MutableLiveData<UserStates>()
    fun getUserModel() = userModel

    fun getData(internetConnectivity: Boolean) {
        userModel.value?.let {
            return
        }
        userModel = mUserRepo.getUserData(internetConnectivity)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("DISPOSE", "----- disposed -------")
        mUserRepo.mCompositeDisposable.clear()
        mUserRepo.mCompositeDisposable.dispose()
    }

}
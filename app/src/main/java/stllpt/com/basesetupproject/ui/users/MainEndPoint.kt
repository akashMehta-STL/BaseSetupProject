package stllpt.com.basesetupproject.ui.users

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import stllpt.com.basesetupproject.shareddata.base.BaseView
import stllpt.com.basesetupproject.shareddata.endpoints.ApiEndPoints
import stllpt.com.basesetupproject.ui.users.model.GitUserResponse
import stllpt.com.basesetupproject.ui.users.model.ItemsItem
import javax.inject.Inject

/**
 * Created by stllpt031 on 23/8/18.
 */
class MainEndPoint @Inject constructor(private val mApi: ApiEndPoints) {

    fun fetchUserList(): Observable<GetApiResponse> {
        return mApi.fetchUserList()
                .observeOn(AndroidSchedulers.mainThread())
                .map<GetApiResponse> {
                    it.body()?.let { it1 ->
                        GetApiResponse.Success(it1)
                    } ?: GetApiResponse.NullResponseError

                }.onErrorReturn { GetApiResponse.WrongResponseError }
    }
}

sealed class GetApiResponse {
    data class Success(val gitUserResponse: GitUserResponse) : GetApiResponse()
    object WrongResponseError : GetApiResponse()
    object NullResponseError : GetApiResponse()
}
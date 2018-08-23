package stllpt.com.basesetupproject.ui.users

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import stllpt.com.basesetupproject.shareddata.base.BaseView
import stllpt.com.basesetupproject.shareddata.endpoints.ApiEndPoints
import stllpt.com.basesetupproject.ui.users.model.ItemsItem
import javax.inject.Inject

/**
 * Created by stllpt031 on 23/8/18.
 */
class MainPresenter @Inject constructor(private val mApi: ApiEndPoints) {

    lateinit var view: View

    fun injectView(view: View) {
        this.view = view
    }

    fun fetchUserList() {
        mApi.fetchUserList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            it.body()?.items?.let { it1 -> view.onContentLoaded(it1) }
                        },
                        onError = {
                            it.printStackTrace()
                        }
                ).addTo(view.compositeDisposable)

    }

    interface View : BaseView {

        fun onContentLoaded(itemList: List<ItemsItem>)
    }
}
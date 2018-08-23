package stllpt.com.basesetupproject.ui.users

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_main.*
import stllpt.com.basesetupproject.AppApplication
import stllpt.com.basesetupproject.MainActivity
import stllpt.com.basesetupproject.R
import stllpt.com.basesetupproject.common.extensions.gone
import stllpt.com.basesetupproject.common.extensions.visible
import stllpt.com.basesetupproject.ui.users.model.ItemsItem
import javax.inject.Inject

/**
 * Created by stllpt031 on 23/8/18.
 */
class MainFragment : Fragment(), MainPresenter.View {
    override val mContext: Context?
        get() = context

    override val compositeDisposable: CompositeDisposable
        get() = CompositeDisposable()
    private val stringItemList = ArrayList<String>()

    @Inject
    lateinit var mPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ((context as MainActivity).application as AppApplication).mComponent.inject(this)
        mPresenter.injectView(this)
        mPresenter.fetchUserList()
        lvContent.gone()
        pbContent.visible()
    }

    override fun onContentLoaded(itemList: List<ItemsItem>) {
        itemList.map {
            it.name
        }.forEach {
            it?.let { it1 -> stringItemList.add(it1) }
        }
        lvContent.adapter = ArrayAdapter<String>(context, android.R.layout.simple_expandable_list_item_1, stringItemList)
        lvContent.visible()
        pbContent.gone()
    }
}
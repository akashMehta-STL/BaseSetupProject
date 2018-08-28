package stllpt.com.basesetupproject.ui.users.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
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
import stllpt.com.basesetupproject.common.extensions.isVisible
import stllpt.com.basesetupproject.common.extensions.visible
import stllpt.com.basesetupproject.shareddata.base.BaseFragment
import stllpt.com.basesetupproject.ui.users.model.ItemsItem
import javax.inject.Inject

/**
 * Created by stllpt031 on 23/8/18.
 */
class UserFragment : BaseFragment() {
    private val stringItemList = ArrayList<String>()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mViewModel: UserViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[UserViewModel::class.java]
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ((context as MainActivity).application as AppApplication).mComponent.inject(this)
        mViewModel.getData(internetConnectivity)
        mViewModel.getUserModel().observe(this, Observer {
            it?.let { userStates ->
                pbContent.visibility = userStates.progress.isVisible()
                userStates.data?.let {
                    lvContent.visible()
                    stringItemList.clear()
                    stringItemList.addAll(it.data)
                    lvContent.adapter = ArrayAdapter<String>(context, android.R.layout.simple_expandable_list_item_1, stringItemList)

                } ?: lvContent.gone()
                userStates.error?.let {
                    it.show?.let {
                        tvError.visibility = it.isVisible()
                    }
                    tvError.text = it.msg
                }
            }
        })
    }

}
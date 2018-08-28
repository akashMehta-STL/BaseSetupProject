package stllpt.com.basesetupproject.shareddata.base

import android.os.Bundle
import android.support.v4.app.Fragment
import stllpt.com.basesetupproject.common.extensions.isInternetAvailable

/**
 * Created by stllpt031 on 14/8/18.
 */
open class BaseFragment : Fragment(){
    var internetConnectivity : Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            internetConnectivity = it.isInternetAvailable()
        }
    }
}
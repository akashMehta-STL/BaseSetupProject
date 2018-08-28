package stllpt.com.basesetupproject.ui.users.model

/**
 * Created by stllpt031 on 28/8/18.
 */
data class Error(val msg: String, val show: Boolean)

data class ResponseData(val data: ArrayList<String>)

data class UserStates(
        val error: Error? = null,
        val progress: Boolean = false,
        val data: ResponseData? = null
)


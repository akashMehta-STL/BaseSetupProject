package stllpt.com.basesetupproject.shareddata.models

import com.google.gson.JsonElement

/**
 * Created by stllpt031 on 14/8/18.
 */
data class GlobalResponse(val message: String,
                          val data: JsonElement)
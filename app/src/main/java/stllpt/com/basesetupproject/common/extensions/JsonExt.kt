package stllpt.com.basesetupproject.common.extensions

import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONObject

/**
 * Created by solutelabs on 28/3/18.
 */
fun JSONObject.toGson(): JsonObject {
    return Gson().fromJson<JsonObject>(this.toString(), JsonObject::class.java)
}
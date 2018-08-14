package stllpt.com.basesetupproject.common.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import stllpt.com.basesetupproject.shareddata.models.GlobalResponse

/**
 * Created by stllpt031 on 17/2/18.
 */
/**
 * This class will be used when json array is returned in data object in Json
 * @param targetObj: This will be object of array item class.
 * @return this will return object list in data.
 */
fun Response<GlobalResponse>.getObjectList(targetObj: Any): Any {
    return if (this.body()?.data != null) {
        Gson().fromJson(this.body()?.data?.asJsonArray,
                TypeToken.getParameterized(ArrayList::class.java, targetObj::class.java).type)
    } else {
        ArrayList<Any>()
    }
}


/**
 * This method will be used when json object is returned is object in json.
 * @param targetObj: This will be the object of class parsed by data.
 * @return this will return data object.
 */
fun Response<GlobalResponse>.getObject(targetObj: Any): Any = Gson().fromJson(this.body()?.data?.asJsonObject,
        TypeToken.getParameterized(targetObj::class.java).type)

package stllpt.com.basesetupproject.shareddata.endpoints

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import stllpt.com.basesetupproject.ui.users.model.GitUserResponse

/**
 * Created by stllpt031 on 14/8/18.
 */
interface ApiEndPoints {
    @GET("search/code")
    fun fetchUserList(@Query("q") user: String = "addClass+user:mozilla",
                      @Query("per_page") perPage: Int = 10,
                      @Query("page") page: Int = 1): Observable<Response<GitUserResponse>>
}
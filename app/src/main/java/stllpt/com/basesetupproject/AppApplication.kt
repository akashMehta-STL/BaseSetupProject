package stllpt.com.basesetupproject

import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import stllpt.com.basesetupproject.injection.component.AppComponent
import stllpt.com.basesetupproject.injection.component.DaggerAppComponent
import stllpt.com.basesetupproject.injection.module.RetrofitModule
import stllpt.com.basesetupproject.injection.module.SessionHolderModule

/**
 * Created by stllpt031 on 14/8/18.
 */
class AppApplication : MultiDexApplication() {
    lateinit var mComponent: AppComponent

    companion object {

        private lateinit var instance: AppApplication

        fun getAppContext(): AppApplication {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        initMultiDex()
        initDagger()
    }

    private fun initDagger() {
        mComponent = DaggerAppComponent.builder()
                .retrofitModule(RetrofitModule())
                .sessionHolderModule(SessionHolderModule())
                .build()
    }

    private fun initMultiDex() {
        MultiDex.install(this)
    }

}
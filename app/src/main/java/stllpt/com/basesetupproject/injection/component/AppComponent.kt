package stllpt.com.basesetupproject.injection.component

import dagger.Component
import stllpt.com.basesetupproject.injection.module.RetrofitModule
import stllpt.com.basesetupproject.injection.module.SessionHolderModule
import stllpt.com.basesetupproject.injection.module.ViewModelModule
import stllpt.com.basesetupproject.ui.users.view.UserFragment
import javax.inject.Singleton

/**
 * Created by stllpt031 on 14/8/18.
 */
@Singleton
@Component(modules = [
    RetrofitModule::class,
    SessionHolderModule::class,
    ViewModelModule::class
])
interface AppComponent {
    fun inject(fragment: UserFragment)
}
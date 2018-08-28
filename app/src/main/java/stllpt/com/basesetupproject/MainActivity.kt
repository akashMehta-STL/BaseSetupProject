package stllpt.com.basesetupproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import stllpt.com.basesetupproject.ui.users.view.UserFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.flContent, UserFragment()).commit()
    }
}

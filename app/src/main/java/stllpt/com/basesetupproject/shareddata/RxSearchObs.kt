package stllpt.com.basesetupproject.shareddata

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by solutelabs on 2/4/18.
 */
object RxSearchObs {

    fun fromView(searchView: EditText): Observable<String> {

        val subject = PublishSubject.create<String>()


        searchView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                p0?.let { subject.onNext(it.toString()) }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        return subject
    }
}
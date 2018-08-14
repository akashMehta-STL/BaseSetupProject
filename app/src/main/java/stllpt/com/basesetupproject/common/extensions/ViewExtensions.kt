package stllpt.com.basesetupproject.common.extensions

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.net.Uri
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import java.io.File
import com.bumptech.glide.load.engine.GlideException
import stllpt.com.basesetupproject.R


fun View.snack(@StringRes msg: Int) {
    Snackbar.make(this, context.getString(msg), Snackbar.LENGTH_SHORT).show()
}

fun View.snack(msg: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, msg, duration).show()
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.inVisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun ImageView.loadImage(url: String?, height: Int? = null, width: Int? = null,
                        default: Int = R.mipmap.ic_launcher) {
    if (height != null && width != null) {
        Glide.with(this.context)
                .load(url)
                .apply(RequestOptions()
                        .placeholder(default)
                        .error(default)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .into(this)
    } else {
        Glide.with(this.context)
                .load(url)
                .apply(RequestOptions()
                        .placeholder(default)
                        .error(default)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                )
                .into(this)
    }
}

fun ImageView.loadImageWithProgressBar(url: String, progressBar: ProgressBar) {
    progressBar.visible()
    this.inVisible()
    Glide.with(this.context)
            .load(url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    progressBar.gone()
                    this@loadImageWithProgressBar.visible()
                    return false
                }

                override fun onResourceReady(resource: Drawable, model: Any, target: com.bumptech.glide.request.target.Target<Drawable>?, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                    progressBar.gone()
                    this@loadImageWithProgressBar.visible()
                    return false
                }
            }).into(this)

}

fun ImageView.loadImage(@DrawableRes res: Int, height: Int? = null, width: Int? = null) {
    if (height != null && width != null) {
        Glide.with(this.context)
                .load(res)
                .apply(RequestOptions().override(height, width))
                .into(this)

    } else {
        Glide.with(this.context)
                .load(res)
                .into(this)
    }
}

fun ImageView.loadImage(uri: Uri, height: Int? = null, width: Int? = null) {
    if (height != null && width != null) {
        Glide.with(this.context)
                .load(uri)
                .apply(RequestOptions().override(height, width))
                .into(this)

    } else {
        Glide.with(this.context)
                .load(uri)
                .into(this)
    }
}


fun ImageView.loadImage(file: File, height: Int? = null, width: Int? = null) {
    if (height != null && width != null) {
        Glide.with(this.context).asBitmap()
                .load(file)
                .apply(RequestOptions().override(height, width))
                .into(this)

    } else {
        Glide.with(this.context).asBitmap()
                .load(file)
                .into(this)
    }
}

val Int.toPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Int.toDp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

fun getSmallCardWidth(): Int {
    return ((Resources.getSystem().displayMetrics.widthPixels) / 1.8).toInt()
}

fun getBigCardWidth(): Int {
    return (((Resources.getSystem().displayMetrics.widthPixels) * 85) / 100)
}
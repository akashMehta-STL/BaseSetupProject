package stllpt.com.basesetupproject.common.extensions

import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import android.util.Size
import android.util.SizeF
import android.util.SparseArray
import java.io.Serializable

/**
 * Created by stllpt031 on 29/8/18.
 */

operator fun Bundle.set(key: String, value: Short) {
    putShort(key, value)
}

operator fun Bundle.set(key: String, value: Int) {
    putInt(key, value)
}

operator fun Bundle.set(key: String, value: Float) {
    putFloat(key, value)
}

operator fun Bundle.set(key: String, value: FloatArray) {
    putFloatArray(key, value)
}

operator fun Bundle.set(key: String, value: Char) {
    putChar(key, value)
}

operator fun Bundle.set(key: String, value: CharArray) {
    putCharArray(key, value)
}

operator fun Bundle.set(key: String, value: CharSequence) {
    putCharSequence(key, value)
}

operator fun Bundle.set(key: String, value: Array<CharSequence>) {
    putCharSequenceArray(key, value)
}

operator fun Bundle.set(key: String, value: Byte) {
    putByte(key, value)
}

operator fun Bundle.set(key: String, value: ByteArray) {
    putByteArray(key, value)
}

operator fun Bundle.set(key: String, value: String) {
    putString(key, value)
}

operator fun Bundle.set(key: String, value: Array<String>) {
    putStringArray(key, value)
}

operator fun Bundle.set(key: String, value: Serializable) {
    putSerializable(key, value)
}

operator fun Bundle.set(key: String, value: Parcelable) {
    putParcelable(key, value)
}

operator fun Bundle.set(key: String, value: IBinder) {
    putBinder(key, value)
}

operator fun Bundle.set(key: String, value: Bundle) {
    putBundle(key, value)
}

operator fun Bundle.set(key: String, value: Array<Parcelable>) {
    putParcelableArray(key, value)
}

operator fun Bundle.set(key: String, value: Size) {
    putSize(key, value)
}

operator fun Bundle.set(key: String, value: SizeF) {
    putSizeF(key, value)
}

operator fun Bundle.set(key: String, value: ShortArray) {
    putShortArray(key, value)
}

operator fun Bundle.set(key: String, value: Boolean) {
    putBoolean(key, value)
}

operator fun Bundle.set(key: String, value: SparseArray<out Parcelable>) {
    putSparseParcelableArray(key, value)
}

operator fun Bundle.set(key: String, value: BooleanArray) {
    putBooleanArray(key, value)
}

operator fun Bundle.set(key: String, value: Double) {
    putDouble(key, value)
}

operator fun Bundle.set(key: String, value: DoubleArray) {
    putDoubleArray(key, value)
}

operator fun Bundle.set(key: String, value: IntArray) {
    putIntArray(key, value)
}

operator fun Bundle.set(key: String, value: Long) {
    putLong(key, value)
}

operator fun Bundle.set(key: String, value: LongArray) {
    putLongArray(key, value)
}

package app.cicilan.component.util

import android.content.res.Resources
import android.icu.text.NumberFormat.getCurrencyInstance
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import com.google.android.material.R.attr
import com.google.android.material.color.MaterialColors
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_SLIDE
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/* Get date for locale */
val currentDate = Calendar.getInstance().timeInMillis

/* Format Rupiah Indonesia */
fun rupiahFormat(number: Int?): String =
    getCurrencyInstance(Locale("in", "ID"))
        .apply { maximumFractionDigits = 0 }
        .format(number)

/* Convert date and time to locale */
fun Long.format(pattern: String): String =
    SimpleDateFormat(pattern, Locale.getDefault(Locale.Category.FORMAT)).format(Date(this))

/* Show soft keyboard for EditText */
fun TextInputEditText.showSoftKeyboard() {
    if (this.requestFocus()) {
        (context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager)
            .showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}

/*  SHOW DIALOG */
fun Fragment.popupDialog(title: String, message: String): MaterialAlertDialogBuilder =
    MaterialAlertDialogBuilder(
        requireContext(),
        1,
        /*R.style.CustomDialog,*/
    )
        .setTitle(title)
        .setMessage(message)
        .setNegativeButton(
            "Cancel",
            /*getString(R.string.cancel_button),*/
        ) { dialog, _ -> dialog.dismiss() }

// /* Hide Soft Keyboard */
// fun TextInputEditText.hideSoftKeyboard() {
//    (context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager)
//        .hideSoftInputFromWindow(this.windowToken, 0)
// }

/* EditText changed event observer */
fun TextInputEditText.afterInputNumberChanged(afterTextChanged: (Int) -> Unit) =
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.getNumber())
        }
    })

fun TextInputEditText.afterInputStringChanged(afterTextChanged: (String?) -> Unit) =
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })

fun MaterialAutoCompleteTextView.afterInputStringChanged(afterTextChanged: (String?) -> Unit) =
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })

/* Convert money format of rupiah */
fun Editable?.getNumber(): Int = this?.filter { it.isDigit() }?.toString()?.toIntOrNull() ?: 0
fun TextInputEditText.addAutoConverterToMoneyFormat(layout: TextInputLayout) =
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable?) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            s?.filter { it.isDigit() }?.toString()?.let {
                removeTextChangedListener(this)
                if (it.isNotEmpty()) {
                    val result = it.toIntOrNull()
                    if (result != null) {
                        val format = NumberFormat.getInstance(Locale("in"))
                            .apply { maximumFractionDigits = 0 }.format(result)
                        setText(format)
                        setSelection(format.length)
                    } else {
                        layout.error = "Max limit"
                        /*context.getString(R.string.input_over)*/
                    }
                } else {
                    text?.clear()
                }

                addTextChangedListener(this)
                layout.error = null
            }
        }
    })

/* Snackbar SHOW UP */
fun Fragment.showMessage(message: String?) = view?.apply {
    Snackbar.make(this, message ?: "Unknown message", Snackbar.LENGTH_SHORT)
        .setAnimationMode(ANIMATION_MODE_SLIDE)
        .setBackgroundTint(MaterialColors.getColor(this, attr.colorSurfaceContainer))
        .setTextColor(MaterialColors.getColor(this, attr.colorOnSurface))
        .show()
}

/* Running in background ViewModel */
fun ViewModel.runInBackground(action: suspend CoroutineScope.() -> Unit) =
    viewModelScope.launch(Dispatchers.IO) { action() }

/* Running in background Fragment */
fun Fragment.runWhenCreated(action: suspend CoroutineScope.() -> Unit) = lifecycleScope.launch {
    repeatOnLifecycle(Lifecycle.State.CREATED) { action() }
}

fun Fragment.runWhenStarted(action: suspend CoroutineScope.() -> Unit) = lifecycleScope.launch {
    repeatOnLifecycle(Lifecycle.State.STARTED) { action() }
}

fun Fragment.runWhenResumed(action: suspend CoroutineScope.() -> Unit) = lifecycleScope.launch {
    repeatOnLifecycle(Lifecycle.State.RESUMED) { action() }
}

/* Running in background Activity */
fun AppCompatActivity.runWhenCreated(action: suspend CoroutineScope.() -> Unit) =
    lifecycleScope.launch { repeatOnLifecycle(Lifecycle.State.CREATED) { action() } }

fun Int.dotPixel() = (this.toFloat() * Resources.getSystem().displayMetrics.density).toInt()

/* PLAY SHAKE ANIMATION */
fun View.vibrate() = getSystemService(context, Vibrator::class.java)
    ?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            it.vibrate(
                VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE),
            )
        } else {
            @Suppress("DEPRECATION")
            it.vibrate(300)
        }
    }

package app.cicilan.component.customview.button

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import app.cicilan.component.R
import com.google.android.material.divider.MaterialDivider

class CustomButtonMenuThird : ConstraintLayout {
    private lateinit var title: TextView
    private lateinit var content: TextView
    private lateinit var divider: MaterialDivider

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private fun initView(context: Context, attrs: AttributeSet) {
        inflate(context, R.layout.custom_button_menu_third, this)

        val title = findViewById<TextView>(R.id.titleButtonThird)
        val content = findViewById<TextView>(R.id.contentButtonThird)
        val divider = findViewById<MaterialDivider>(R.id.dividerButtonThird)

        context.obtainStyledAttributes(attrs, R.styleable.CustomButtonMenuThird, 0, 0).apply {
            try {
                title.text = getString(R.styleable.CustomButtonMenuThird_titleThird)
                content.text = getString(R.styleable.CustomButtonMenuThird_contentThird)
                divider.apply {
                    /*dividerColor = MaterialColors.getColor(rootView, attrs.)*/
                    visibility = getInt(R.styleable.CustomButtonMenuThird_hideDividerThird, 0)
                }
            } finally {
                recycle()
            }
        }
    }

    /*init {
        inflate(context, R.layout.custom_button_menu_third, this)
        context.obtainStyledAttributes(attrs, R.styleable.CustomButtonMenuThird, 0, 0)
            .apply {
                val title = findViewById<TextView>(R.id.titleButtonThird)
                val content = findViewById<TextView>(R.id.contentButtonThird)
                val divider = findViewById<MaterialDivider>(R.id.dividerButtonThird)
                try {
                    title.text = getString(R.styleable.CustomButtonMenuThird_titleThird)
                    content.text = getString(R.styleable.CustomButtonMenuThird_contentThird)
                    divider.apply {
                        dividerColor = MaterialColors.getColor(rootView, attr.colorSurfaceVariant)
                        visibility = getInt(R.styleable.CustomButtonMenuThird_hideDividerThird, 0)
                    }
                } finally {
                    recycle()
                }
            }
    }*/
}

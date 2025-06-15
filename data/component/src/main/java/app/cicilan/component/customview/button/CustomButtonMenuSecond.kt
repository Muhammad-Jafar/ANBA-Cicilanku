package app.cicilan.component.customview.button

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import app.cicilan.component.R
import com.google.android.material.color.MaterialColors
import com.google.android.material.divider.MaterialDivider

class CustomButtonMenuSecond : ConstraintLayout {
    private lateinit var titleItem: TextView
    private lateinit var contentItem: TextView

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @SuppressLint("CustomViewStyleable")
    private fun initView(context: Context, attrs: AttributeSet) {
        inflate(context, R.layout.custom_layout_item, this)

        titleItem = findViewById(R.id.titleLayoutItem)
        contentItem = findViewById(R.id.contentLayoutItem)
        val divider = findViewById<MaterialDivider>(R.id.dividerLayoutItem)

        context.obtainStyledAttributes(attrs, R.styleable.CustomLayoutItem, 0, 0).apply {
            try {
                titleItem.text = getString(R.styleable.CustomLayoutItem_titleItem)
                contentItem.text = getString(R.styleable.CustomLayoutItem_contentItem)
                divider.apply {
                    dividerColor =
                        MaterialColors.getColor(rootView, com.google.android.material.R.attr.colorSurfaceVariant)
                    visibility = getInt(R.styleable.CustomLayoutItem_hideDividerItem, 0)
                }
            } finally {
                recycle()
            }
        }
    }
    /*init {
        inflate(context, R.layout.custom_button_menu_second, this)
        context.obtainStyledAttributes(attrs, R.styleable.CustomButtonMenuSecond, 0, 0)
            .apply {
                val icon = findViewById<ShapeableImageView>(R.id.iconButtonSecond)
                val title = findViewById<TextView>(R.id.titleButtonSecond)
                val divider = findViewById<MaterialDivider>(R.id.dividerButtonSecond)
                try {
                    icon.setImageResource(getResourceId(R.styleable.CustomButtonMenuSecond_iconSecond, 0))
                    title.text = getString(R.styleable.CustomButtonMenuSecond_titleSecond)
                    divider.apply {
                        dividerColor = MaterialColors.getColor(rootView, attr.colorSurfaceVariant)
                        visibility = getInt(R.styleable.CustomButtonMenuSecond_hideDividerSecond, 0)
                    }
                } finally {
                    recycle()
                }
            }
    }*/
}

package app.cicilan.customview.button

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import app.cicilan.customview.R
import com.google.android.material.color.MaterialColors
import com.google.android.material.divider.MaterialDivider

class CustomButtonMenu : ConstraintLayout {
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
        inflate(context, R.layout.custom_button_menu, this)
        context.obtainStyledAttributes(attrs, R.styleable.CustomButtonMenu, 0, 0)
            .apply {
                val icon = findViewById<ShapeableImageView>(R.id.iconButtonMenu)
                val title = findViewById<TextView>(R.id.titleButtonMenu)
                val subtitle = findViewById<TextView>(R.id.subtitleButtonMenu)
                try {
                    icon.setImageResource(getResourceId(R.styleable.CustomButtonMenu_icon, 0))
                    title.text = getString(R.styleable.CustomButtonMenu_title)
                    subtitle.text = getString(R.styleable.CustomButtonMenu_subtitle)
                } finally {
                    recycle()
                }
            }
    }*/
}

/*
class CustomButtonMenu(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    init {
        inflate(context, R.layout.custom_button_menu, this)
        context.obtainStyledAttributes(attrs, R.styleable.CustomButtonMenu, 0, 0)
            .apply {
                val icon = findViewById<ShapeableImageView>(R.id.iconButtonMenu)
                val title = findViewById<TextView>(R.id.titleButtonMenu)
                val subtitle = findViewById<TextView>(R.id.subtitleButtonMenu)
                try {
                    icon.setImageResource(getResourceId(R.styleable.CustomButtonMenu_icon, 0))
                    title.text = getString(R.styleable.CustomButtonMenu_title)
                    subtitle.text = getString(R.styleable.CustomButtonMenu_subtitle)
                } finally {
                    recycle()
                }
            }
    }
}*/

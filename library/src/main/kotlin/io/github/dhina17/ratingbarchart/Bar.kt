package io.github.dhina17.ratingbarchart

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import androidx.core.view.setPadding
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.textview.MaterialTextView

class Bar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val labelView: MaterialTextView by lazy {
        MaterialTextView(context)
    }

    private val progressBar: LinearProgressIndicator by lazy {
        LinearProgressIndicator(context).apply {
            isIndeterminate = false
            max = MAX_BAR_VALUE
            gravity = Gravity.CENTER
        }
    }

    init {
        // default orientation is horizontal.
        setupView()
    }

    private fun setupView() {
        // Add the label and progress bar in parent
        addView(labelView)
        addView(progressBar)
    }

    @JvmOverloads
    fun setupBarContent(
        label: String,
        barPadding: Int,
        barThickness: Int,
        barCornerRadius: Int,
        barTrackColor: Int? = null
    ) {
        labelView.apply {
            setPadding(barPadding)
            text = label
        }
        progressBar.apply {
            setPadding(barPadding)
            trackThickness = barThickness
            trackCornerRadius = barCornerRadius
            barTrackColor?.let { trackColor = it }
        }
    }

    fun setBarValue(value: Int) {
        progressBar.progress = value
    }

    companion object {
        // Total value of the bar progress
        private const val MAX_BAR_VALUE = 100
    }
}
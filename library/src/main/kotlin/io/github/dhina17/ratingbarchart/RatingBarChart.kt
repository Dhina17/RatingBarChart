package io.github.dhina17.ratingbarchart

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.view.setPadding
import com.google.android.material.progressindicator.LinearProgressIndicator

class RatingBarChart @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        // We need to add the views in column-like manner
        orientation = VERTICAL
        // Set up the bars
        setupBars()
    }

    private fun setupBars() {
        repeat(MAX_BAR_COUNT) {
            val bar = LinearProgressIndicator(context).apply {
                // Later move to a style.
                isIndeterminate = false
                progress = 50
                max = MAX_BAR_VALUE
                setPadding(10)
                trackCornerRadius = 100
                trackThickness = 20
            }
            addView(bar)
        }
    }


    companion object {
        // Total bars in the chart
        private const val MAX_BAR_COUNT = 5

        // Total value of the bar progress
        private const val MAX_BAR_VALUE = 100
    }
}

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

    // Bar values
    // Index - whose values
    // 0 - 5 star
    // 1 - 4 star
    // 2 - 3 star
    // 3 - 2 star
    // 4 - 1 star
    private var barValues = IntArray(MAX_BAR_COUNT)

    private val bars = mutableListOf<LinearProgressIndicator>()

    private var barRadius: Int = DEFAULT_BAR_RADIUS

    init {
        // We need to add the views in column-like manner
        orientation = VERTICAL
        // Obtain attrs
        obtainAttrs(context, attrs)
        // Set up the bars
        setupBars()
    }

    private fun obtainAttrs(context: Context, attrs: AttributeSet?) {
        val attributes =
            context.obtainStyledAttributes(attrs, R.styleable.RatingBarChart)
        // Retrieve the radius from the attributes
        barRadius = attributes.getDimensionPixelSize(
            R.styleable.RatingBarChart_barRadius,
            DEFAULT_BAR_RADIUS.px
        )
    }

    private fun setupBars() {
        repeat(MAX_BAR_COUNT) {
            // Create a bar (LinearProgressIndicator)
            val bar = LinearProgressIndicator(context).apply {
                // Later move to a style.
                isIndeterminate = false
                max = MAX_BAR_VALUE
                setPadding(10)
                trackThickness = 20
                trackCornerRadius = barRadius

            }
            // Add the bar to the bars list.
            bars.add(bar)
            // Add the view to the parent.
            addView(bar)
        }
    }

    private fun applyBarValues() {
        // Set the values to the bars
        bars.forEachIndexed { index, bar ->
            bar.progress = barValues[index]
        }
    }

    fun setBarValues(values: IntArray) {
        barValues = values
        applyBarValues()
    }

    companion object {
        // Total bars in the chart
        private const val MAX_BAR_COUNT = 5

        // Total value of the bar progress
        private const val MAX_BAR_VALUE = 100

        // Default bar radius
        private const val DEFAULT_BAR_RADIUS = 0
    }
}

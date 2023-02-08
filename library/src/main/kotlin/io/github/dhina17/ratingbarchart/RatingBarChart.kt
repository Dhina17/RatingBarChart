package io.github.dhina17.ratingbarchart

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

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

    private val bars = mutableListOf<Bar>()

    private var barRadius: Int = DEFAULT_BAR_RADIUS

    private var barThickness: Int = DEFAULT_BAR_THICKNESS

    private var barPadding: Int = DEFAULT_BAR_PADDING

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
            DEFAULT_BAR_RADIUS
        )
        // Retrieve the bar thickness from the attributes
        barThickness = attributes.getDimensionPixelSize(
            R.styleable.RatingBarChart_barThickness,
            DEFAULT_BAR_THICKNESS
        )
        // Retrieve the bar padding from the attributes
        barPadding = attributes.getDimensionPixelSize(
            R.styleable.RatingBarChart_barPadding,
            DEFAULT_BAR_PADDING
        )
    }

    private fun setupBars() {
        for (index in MAX_BAR_COUNT downTo 1) {
            // Create a bar (LinearProgressIndicator)
            val bar = Bar(context).apply {
                setupBarContent(
                    label = "$index",
                    barPadding = barPadding,
                    barThickness = barThickness,
                    barCornerRadius = barRadius
                )
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
            bar.setBarValue(barValues[index])
        }
    }

    fun setBarValues(values: IntArray) {
        barValues = values
        applyBarValues()
    }

    companion object {
        // Total bars in the chart
        private const val MAX_BAR_COUNT = 5

        // Default bar radius
        private val DEFAULT_BAR_RADIUS = 0.px

        // Default bar thickness
        private val DEFAULT_BAR_THICKNESS = 10.px

        // Default bar padding
        private val DEFAULT_BAR_PADDING = 10.px
    }
}

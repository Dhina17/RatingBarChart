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

    private var barTrackColor: Int? = null

    private var barLabels: Array<CharSequence>? = null

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
        // Retrieve the bar track color from the attributes.
        // Pass 0 as default value but it is used no where.
        // We let the LinearProgressIndicator's default.
        barTrackColor = attributes.getColor(R.styleable.RatingBarChart_barTrackColor, 0)

        // Retrieve the bar label from the attributes
        barLabels = attributes.getTextArray(R.styleable.RatingBarChart_barLabels)

        // Recycle the attributes array
        attributes.recycle()
    }

    private fun setupBars() {
        for (index in MAX_BAR_COUNT downTo 1) {
            // Create a bar (LinearProgressIndicator)
            val bar = Bar(context).apply {
                setupBarContent(
                    label = (barLabels?.get(index - 1)?.toString() ?: "$index"),
                    barPadding = barPadding,
                    barThickness = barThickness,
                    barCornerRadius = barRadius,
                    barTrackColor = if (barTrackColor == 0) null else barTrackColor
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

    /**
     * Bar end labels
     * Index - whose values
     * 0 - 5 star
     * 1 - 4 star
     * 2 - 3 star
     * 3 - 2 star
     * 4 - 1 star
     */
    fun setBarEndLabels(values: List<String>) {
        bars.forEachIndexed { index, bar ->
            bar.setBarEndLabel(values[index])
        }
    }

    companion object {
        // Total bars in the chart
        private const val MAX_BAR_COUNT = 5

        // Default bar radius
        private val DEFAULT_BAR_RADIUS = 0.px

        // Default bar thickness
        private val DEFAULT_BAR_THICKNESS = 10.px

        // Default bar padding
        private val DEFAULT_BAR_PADDING = 3.px
    }
}

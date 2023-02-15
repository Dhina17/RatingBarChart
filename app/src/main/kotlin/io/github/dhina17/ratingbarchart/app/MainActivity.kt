package io.github.dhina17.ratingbarchart.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.dhina17.ratingbarchart.RatingBarChart
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ratingBarChart = findViewById<RatingBarChart>(R.id.ratingBarChart)
        val values = intArrayOf(
            Random.Default.nextInt(100),
            Random.Default.nextInt(100),
            Random.Default.nextInt(100),
            Random.Default.nextInt(100),
            Random.Default.nextInt(100),
        )
        val endLabels = listOf("17", "13", "05", "06", "07")
        ratingBarChart.setBarValues(values)
        ratingBarChart.setBarLabels(endLabels)
    }
}
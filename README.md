# RatingBarChart

A simple and customizable Android library to display a rating bar chart.

## Getting Started

### Prerequisites

This library requires a minimum SDK level of 21.

### Installing

Add the following line to your project build.gradle file:


```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

And add the following dependency in your module build.gradle:

```
implementation 'com.github.Dhina17:RatingBarChart:main-SNAPSHOT'
```

### Usage

Add the RatingBarChart to your layout:

```
<io.github.dhina17.ratingbarchart.RatingBarChart
        android:id="@+id/ratingBarChart"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:layout_weight="1"
        android:padding="10dp"
        app:barPadding="3dp"
        app:barRadius="10dp"
        app:barThickness="10dp" />
```

Set the bar values

```
val ratingBarChart = findViewById<RatingBarChart>(R.id.ratingBarChart)
// Create an int array in the following order for setting bar values.
// value should be <= 100.
val values = intArrayOf(
     // Index 0 - Five star
     Random.Default.nextInt(100),
     // Index 1 - Four star
     Random.Default.nextInt(100),
     // Index 2 - Three star
     Random.Default.nextInt(100),
     // Index 3 - Two star
     Random.Default.nextInt(100),
     // Index 4 - One star
     Random.Default.nextInt(100),
)
ratingBarChart.setBarValues(values)

```

### Attributes

| Attribute        | Description              | Default value |
|------------------|--------------------------|---------------|
| app:barPadding   | Padding of the bar       | 3dp           |
| app:barRadius    | Corner radius of the bar | 3dp           |
| app:barThickness | Thickness of the bar     | 10dp          |

### Screenshots

#### Standalone RatingBarChart

<img src="/screenshots/rating_bar_chart.png" width="270" height="585">


#### RatingBarChart usage

Refer `app/src/main/res/layout/activity_main.xml`

<img src="/screenshots/rating_bar_chart_usage.png" width="270" height="585">


## Contributions

Your contributions are always welcome! Please submit a pull request or create an issue to add new features or fix a bug.

## License

This project is licensed under the Apache License 2.0 - see the LICENSE file for details.

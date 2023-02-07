package io.github.dhina17.ratingbarchart

import android.content.res.Resources

val Int.px
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()
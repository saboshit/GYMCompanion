package com.sabdev.gymcompanion.domain.model

import com.sabdev.gymcompanion.R

sealed class RoutInfo(val name: Int, val description: Int, val image: Int) {
    object Mon : RoutInfo(R.string.rou1, R.string.desc1, R.drawable.routines)
    object Tue : RoutInfo(R.string.rou2, R.string.desc2, R.drawable.routines)
    object Wed : RoutInfo(R.string.rou3, R.string.desc3, R.drawable.routines)
    object Thu : RoutInfo(R.string.rou1, R.string.desc1, R.drawable.routines)
    object Fri : RoutInfo(R.string.rou2, R.string.desc2, R.drawable.routines)
}
package com.jfalstaff.qrity.presentation

import com.jfalstaff.qrity.domain.*

fun checkColor(selectedColor: String?): String {
    return when (selectedColor) {
        BLACK_COLOR -> BLACK_COLOR_CODE
        RED_COLOR -> RED_COLOR_CODE
        ORANGE_COLOR -> ORANGE_COLOR_CODE
        BLUE_COLOR -> BLUE_COLOR_CODE
        GREEN_COLOR -> GREEN_COLOR_CODE
        else -> {
            BLACK_COLOR_CODE
        }
    }
}
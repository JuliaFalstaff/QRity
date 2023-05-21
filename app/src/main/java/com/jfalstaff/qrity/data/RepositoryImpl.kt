package com.jfalstaff.qrity.data

import com.jfalstaff.qrity.domain.*
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {
    private val colorList = listOf(
        BLACK_COLOR,
        RED_COLOR,
        ORANGE_COLOR,
        BLUE_COLOR,
        GREEN_COLOR
    )

    override fun getColorList(): List<String> {
        return colorList
    }
}
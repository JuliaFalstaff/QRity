package com.jfalstaff.qrity.data

import com.jfalstaff.qrity.domain.ColorCode
import com.jfalstaff.qrity.domain.Repository

class RepositoryImpl() : Repository {
    private val colorList = listOf(
        ColorCode("Black", "FFFFFF"),
        ColorCode("Red", "FF0000"),
        ColorCode("Orange", "FF7400"),
        ColorCode("Blue", "1240AB"),
        ColorCode("Green", "007B25")
    )

    override fun getColorList(): List<ColorCode> {
        return colorList
    }
}
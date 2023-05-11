package com.jfalstaff.qrity.domain

interface Repository {
    fun getColorList(): List<ColorCode>
}
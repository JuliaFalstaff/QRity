package com.jfalstaff.qrity.domain

import javax.inject.Inject

class GetColorListUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke() = repository.getColorList()
}
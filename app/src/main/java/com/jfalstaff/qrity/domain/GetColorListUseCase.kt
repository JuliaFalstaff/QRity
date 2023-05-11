package com.jfalstaff.qrity.domain

class GetColorListUseCase(private val repository: Repository) {

    operator fun invoke() = repository.getColorList()
}
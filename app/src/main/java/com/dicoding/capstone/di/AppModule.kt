package com.dicoding.capstone.di

import com.dicoding.capstone.core.domain.usecase.GameInteractor
import com.dicoding.capstone.core.domain.usecase.GameUseCase
import com.dicoding.capstone.ui.detail.DetailViewModel
import com.dicoding.capstone.ui.home.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GameUseCase> { GameInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
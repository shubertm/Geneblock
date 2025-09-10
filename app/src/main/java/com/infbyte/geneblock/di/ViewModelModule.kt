package com.infbyte.geneblock.di

import com.infbyte.geneblock.presentation.viewmodels.GenesisViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::GenesisViewModel)
}
package com.infbyte.geneblock.di

import com.infbyte.geneblock.data.repo.GenesisRepo
import com.infbyte.geneblock.data.repo.Repo
import org.koin.dsl.module

val repoModule =
    module {
        single<Repo> { GenesisRepo(get()) }
    }

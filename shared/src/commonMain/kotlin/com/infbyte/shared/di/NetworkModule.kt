package com.infbyte.shared.di

import com.infbyte.shared.models.Block
import com.infbyte.shared.network.Client
import com.infbyte.shared.network.GenesisClient
import org.koin.dsl.module

val networkModule =
    module {
        single<Client<Block>> { GenesisClient() }
    }

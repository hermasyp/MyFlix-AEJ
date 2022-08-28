package com.catnip.core.base

import org.koin.core.module.Module

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface BaseModules {
    fun getModules() : List<Module>
}

interface FeatureModules : BaseModules{
    val repositories : Module
    val viewModels : Module
    val dataSources : Module
    val useCases : Module
    val network : Module
}
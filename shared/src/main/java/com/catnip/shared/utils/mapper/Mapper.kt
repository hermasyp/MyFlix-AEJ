package com.catnip.shared.utils

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

interface Mapper<DTO,ViewParam>

interface ViewParamMapper<DTO, ViewParam> : Mapper<DTO,ViewParam> {
    fun toViewParam(dataObject: DTO?): ViewParam
}

interface DataObjectMapper<DTO, ViewParam> : Mapper<DTO,ViewParam> {
    fun toDataObject(viewParam: ViewParam?): DTO
}

interface DataMapper<DTO, ViewParam> : ViewParamMapper<DTO, ViewParam>, DataObjectMapper<DTO, ViewParam>

class ListMapper<DTO, ViewParam>(private val mapper: Mapper<DTO, ViewParam>) {
    fun toDataObjects(viewParams: List<ViewParam>?): List<DTO> {
        if (mapper !is DataObjectMapper) throw IllegalStateException("Needs mapper DataObjectMapper.kt")
        if (viewParams.isNullOrEmpty()) return listOf()
        return viewParams.map {
            mapper.toDataObject(it)
        }
    }

    fun toViewParams(dataObjects: List<DTO>?): List<ViewParam> {
        if (mapper !is ViewParamMapper) throw IllegalStateException("Needs mapper ViewParamMapper.kt")
        if (dataObjects.isNullOrEmpty()) return listOf()
        return dataObjects.map {
            mapper.toViewParam(it)
        }
    }
}
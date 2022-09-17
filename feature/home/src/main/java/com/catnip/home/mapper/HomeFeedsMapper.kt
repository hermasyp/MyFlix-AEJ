package com.catnip.home.mapper

import com.catnip.home.data.network.model.response.HomeFeedsResponse
import com.catnip.home.data.network.model.response.SectionResponse
import com.catnip.home.presentation.viewparam.HomeFeedsViewParam
import com.catnip.home.presentation.viewparam.SectionViewParam
import com.catnip.shared.data.model.mapper.MovieMapper
import com.catnip.shared.utils.ListMapper
import com.catnip.shared.utils.ViewParamMapper

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

object HomeFeedsMapper : ViewParamMapper<HomeFeedsResponse,HomeFeedsViewParam>{
    override fun toViewParam(dataObject: HomeFeedsResponse?): HomeFeedsViewParam = HomeFeedsViewParam(
        MovieMapper.toViewParam(dataObject?.header),
        ListMapper(SectionMapper).toViewParams(dataObject?.sections)
    )
}

object SectionMapper : ViewParamMapper<SectionResponse,SectionViewParam>{
    override fun toViewParam(dataObject: SectionResponse?): SectionViewParam = SectionViewParam(
        sectionId =  dataObject?.sectionId ?: -1,
        sectionName = dataObject?.sectionName.orEmpty(),
        contents = ListMapper(MovieMapper).toViewParams(dataObject?.contents)
    )
}
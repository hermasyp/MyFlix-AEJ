package com.catnip.home.presentation.viewparam.homeitem

import com.catnip.home.presentation.viewparam.SectionViewParam
import com.catnip.shared.data.model.viewparam.MovieViewParam

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
sealed class HomeUiItem{
    class HeaderSectionItem(val movieViewParam: MovieViewParam) : HomeUiItem()
    class MovieSectionItem(val sectionViewParam: SectionViewParam) : HomeUiItem()
}

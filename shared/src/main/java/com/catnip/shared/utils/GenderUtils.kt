package com.catnip.shared.utils

import android.content.Context
import android.widget.ArrayAdapter

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
object GenderUtils {
    fun createGenderListAdapter(context: Context): ArrayAdapter<String> {
        val genders = context.resources.getStringArray(com.catnip.styling.R.array.gender_list)
        return ArrayAdapter(context, com.catnip.styling.R.layout.simple_list_item, genders)
    }

    fun parseGender(genderText : String) : String{
        return if(genderText == Gender.MALE_OPTION) Gender.MALE else Gender.FEMALE
    }
}

object Gender{
    const val MALE = "0"
    const val MALE_OPTION = "Male"
    const val FEMALE = "1"
    const val FEMALE_OPTION = "Female"
}
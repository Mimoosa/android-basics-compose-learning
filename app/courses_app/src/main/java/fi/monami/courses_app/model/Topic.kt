package fi.monami.courses_app.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(@StringRes val name: Int, val associatedNum: Int, @DrawableRes val image: Int)
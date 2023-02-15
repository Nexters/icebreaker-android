package com.hammer.talkbbokki.presentation.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.hammer.talkbbokki.R
import com.hammer.talkbbokki.ui.theme.Category01
import com.hammer.talkbbokki.ui.theme.Category02
import com.hammer.talkbbokki.ui.theme.Category03
import com.hammer.talkbbokki.ui.theme.Gray06
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _categoryLevel: MutableStateFlow<List<CategoryLevel>> = MutableStateFlow(
        CategoryLevel.values().toList()
    )
    val categoryLevel: StateFlow<List<CategoryLevel>> get() = _categoryLevel.asStateFlow()
}

enum class CategoryLevel(
    val level: String,
    @DrawableRes val icon: Int,
    @StringRes val title: Int,
    val backgroundColor: Color = Color.White
) {
    Level1(
        level = "level1",
        title = R.string.main_level1_title,
        icon = R.drawable.image_category_01,
        backgroundColor = Category01
    ),
    Level2(
        level = "level2",
        title = R.string.main_level2_title,
        icon = R.drawable.image_category_02,
        backgroundColor = Category02
    ),
    Level3(
        level = "level3",
        title = R.string.main_level3_title,
        icon = R.drawable.image_category_03,
        backgroundColor = Category03
    ),
    Level4(
        level = "level4",
        icon = R.drawable.image_category_coming_soon,
        title = R.string.main_level4_title,
        backgroundColor = Gray06
    )
}

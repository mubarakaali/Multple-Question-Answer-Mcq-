package com.fivesol.kapsulemcqs.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import fivesol.networklibrary.domain.models.Resource
import fivesol.networklibrary.domain.models.main_category.Data
import fivesol.networklibrary.domain.models.sub_category.Mcq
import fivesol.networklibrary.domain.usecases.MainCategoryUseCase
import fivesol.networklibrary.domain.usecases.SubCategoryMcqUseCase
import javax.inject.Inject

@HiltViewModel
class MainMcqViewModel @Inject constructor(
    private val subCategoryUseCase: SubCategoryMcqUseCase,
    private val categoryUseCase: MainCategoryUseCase
) :
    ViewModel() {

    private val _subCategoryMcqCase = MutableStateFlow<Resource<List<Mcq>>>(Resource.Loading(emptyList()))
    val subCategoryMcqCase:StateFlow<Resource<List<Mcq>>> = _subCategoryMcqCase

    suspend fun  mainCategory() :StateFlow<Resource<List<Data>>> = categoryUseCase.getMainCategory()

    fun getSubCategoryList(position: Int,categorId:Int) {
        Log.d("jejeje", "MainMcqViewModel getSubCategoryList: position........ $position    categorId...... $categorId")
        var subCategoryPath = "computer-science"
        if (position == 0) {
            subCategoryPath = "current-affair"
        } else if (position == 1) {
            subCategoryPath = "general-knowledge"

        } else if (position == 2) {
            subCategoryPath = "islamic-studies"

        }
         else if (position == 3) {
            subCategoryPath = "pakistan-studies"

        } else if (position == 4) {
            subCategoryPath = "computer-science"
        } else if (position == 5) {
            subCategoryPath = "math"
        } else if (position == 6) {
            subCategoryPath = "everyday_science"
        } else if (position == 7) {
            subCategoryPath = "sports"
        } else if (position == 8) {
            subCategoryPath = "english"
        }
        else if (position == 9) {
            subCategoryPath = "ms-office"
        }
        viewModelScope.launch {
            _subCategoryMcqCase.emitAll(subCategoryUseCase.getSubCategoryMcq(path = subCategoryPath,categorId = categorId))
        }
    }
}

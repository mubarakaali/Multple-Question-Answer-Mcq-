package fivesol.networklibrary.domain.usecases

import kotlinx.coroutines.flow.StateFlow
import fivesol.networklibrary.domain.models.Resource
import fivesol.networklibrary.domain.models.sub_category.Mcq
import fivesol.networklibrary.domain.repository.subcategory.SubCategoryRepository
import fivesol.networklibrary.domain.repository.utils.CachedFirstStrategy
import javax.inject.Inject

class SubCategoryMcqUseCase @Inject constructor(private val subCategoryRepository: SubCategoryRepository) {

    suspend fun getSubCategoryMcq(path:String,categorId:Int):StateFlow<Resource<List<Mcq>>>{
     return subCategoryRepository.getSubCategoriesMcq(path, categorId = categorId,CachedFirstStrategy)
    }
}
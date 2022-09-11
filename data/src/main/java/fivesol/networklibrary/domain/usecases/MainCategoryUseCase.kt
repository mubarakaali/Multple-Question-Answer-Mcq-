package fivesol.networklibrary.domain.usecases

import fivesol.networklibrary.domain.repository.main.MainCategoryRepository
import fivesol.networklibrary.domain.repository.utils.CachedFirstStrategy
import javax.inject.Inject


class MainCategoryUseCase @Inject constructor(private val mainCategoryRepository: MainCategoryRepository) {
    suspend fun getMainCategory() = mainCategoryRepository.getCategoryList(CachedFirstStrategy)
}
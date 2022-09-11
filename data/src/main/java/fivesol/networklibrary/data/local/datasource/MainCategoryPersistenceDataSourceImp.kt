package fivesol.networklibrary.data.local.datasource

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import fivesol.networklibrary.data.local.daos.CategoryDao
import fivesol.networklibrary.domain.datasource.local.MainCategoryPersistenceDataSource
import fivesol.networklibrary.domain.models.main_category.Data
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class MainCategoryPersistenceDataSourceImp @Inject constructor(
    private val categoryDao: CategoryDao
) : MainCategoryPersistenceDataSource {

    override suspend fun getMainCategoryList(): StateFlow<List<Data>> =
        categoryDao.getCategoryList()
            .distinctUntilChanged()
            .map {
                it
            }
            .stateIn(
                CoroutineScope(coroutineContext)
            )


    override suspend fun insertMainCategoryList(mainData: List<Data>) =
        categoryDao.insertAllMainCategory(mainData)
}
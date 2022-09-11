package fivesol.networklibrary.data.local.datasource

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import fivesol.networklibrary.data.local.daos.SubCategoryMcqDao
import fivesol.networklibrary.domain.datasource.local.SubCategoryMcqPersistenceDataSource
import fivesol.networklibrary.domain.models.sub_category.Mcq
import kotlin.coroutines.coroutineContext

class SubCategoryMcqPersistenceDataSourceImp(private val subCategoryMcqDao: SubCategoryMcqDao):SubCategoryMcqPersistenceDataSource {
    override suspend fun getSubMcqCategoryList(categorId:Int): StateFlow<List<Mcq>> {
        return subCategoryMcqDao.getAllSubCategoryMcq(categoryId =categorId ).distinctUntilChanged()
            .map { it }.stateIn(CoroutineScope(coroutineContext))
    }

    override suspend fun insertSubMcqCategoryList( mcqData: List<Mcq>) {
        subCategoryMcqDao.insertAllSubCategoryMcq(mcqData)
    }

}
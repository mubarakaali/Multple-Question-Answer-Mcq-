package fivesol.networklibrary.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import fivesol.networklibrary.domain.repository.main.MainCategoryRepository
import fivesol.networklibrary.domain.repository.main.MainCategoryRepositoryImp
import fivesol.networklibrary.domain.repository.subcategory.SubCategoryRepository
import fivesol.networklibrary.domain.repository.subcategory.SubCategoryRepositoryImp
import fivesol.networklibrary.domain.datasource.local.MainCategoryPersistenceDataSource
import fivesol.networklibrary.domain.datasource.local.SubCategoryMcqPersistenceDataSource
import fivesol.networklibrary.domain.datasource.remote.MainCategoryRemoteDataSource
import fivesol.networklibrary.domain.datasource.remote.SubCategoryRemoteDataSource

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun providesMainCategoryRepository(mainCategoryRDS: MainCategoryRemoteDataSource, mainCategoryPDS: MainCategoryPersistenceDataSource): MainCategoryRepository {
        return MainCategoryRepositoryImp(mainCategoryRDS,mainCategoryPDS)
    }

    @Provides
    @ActivityRetainedScoped
    fun providesSubCategoryRepository(subCategoryRDS: SubCategoryRemoteDataSource,subCategoryPDS:SubCategoryMcqPersistenceDataSource): SubCategoryRepository =
        SubCategoryRepositoryImp(subCategoryRDS,subCategoryPDS)
}
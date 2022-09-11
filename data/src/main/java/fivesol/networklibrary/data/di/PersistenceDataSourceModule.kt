package fivesol.networklibrary.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import fivesol.networklibrary.data.local.appdatabase.AppDatabase
import fivesol.networklibrary.data.local.daos.CategoryDao
import fivesol.networklibrary.data.local.daos.SubCategoryMcqDao
import fivesol.networklibrary.data.local.datasource.MainCategoryPersistenceDataSourceImp
import fivesol.networklibrary.data.local.datasource.SubCategoryMcqPersistenceDataSourceImp
import fivesol.networklibrary.domain.datasource.local.MainCategoryPersistenceDataSource
import fivesol.networklibrary.domain.datasource.local.SubCategoryMcqPersistenceDataSource

@Module
@InstallIn(ActivityRetainedComponent::class)
object PersistenceDataSourceModule {

    @Provides
    @ActivityRetainedScoped
    fun providesCategoryDao(db: AppDatabase): CategoryDao = db.categoryDao()
    @Provides
    @ActivityRetainedScoped
    fun providesSubCategoryMcqDao(db:AppDatabase):SubCategoryMcqDao = db.subCategoryMcq()

   @Provides
   @ActivityRetainedScoped
    fun providesMainCategoryPersistenceDataSource(categoryDao: CategoryDao): MainCategoryPersistenceDataSource =
        MainCategoryPersistenceDataSourceImp(categoryDao)
    @Provides
   @ActivityRetainedScoped
    fun providesSubCategoryMcqPersistenceDataSource(categoryDao: SubCategoryMcqDao): SubCategoryMcqPersistenceDataSource =
        SubCategoryMcqPersistenceDataSourceImp(categoryDao)
}
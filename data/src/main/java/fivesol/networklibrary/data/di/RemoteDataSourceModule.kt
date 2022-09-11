package fivesol.networklibrary.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fivesol.networklibrary.data.remote.api.ApiService
import fivesol.networklibrary.domain.datasource.remote.MainCategoryRemoteDataSource
import fivesol.networklibrary.data.remote.datasource.MainCategoryRemoteDataSourceImp
import fivesol.networklibrary.domain.datasource.remote.SubCategoryRemoteDataSource
import fivesol.networklibrary.data.remote.datasource.SubCategoryRemoteDataSourceImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Singleton
    @Provides
    fun providesMainCategoryDataSource(apiService: ApiService): MainCategoryRemoteDataSource {
        return MainCategoryRemoteDataSourceImp(apiService)
    }

    @Singleton
    @Provides
    fun providesSubCategoryDataSource(apiService: ApiService): SubCategoryRemoteDataSource =
        SubCategoryRemoteDataSourceImp(apiService)
}
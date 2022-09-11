package fivesol.networklibrary.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import fivesol.networklibrary.domain.models.main_category.MainCategoryResponse
import fivesol.networklibrary.domain.models.sub_category.SubCategoryResponse
import fivesol.networklibrary.data.utils.ApiConstants.CATEGORIES

interface ApiService {

    @GET(CATEGORIES)
    suspend fun getPostList(): Response<MainCategoryResponse>

    @GET("mcq-by-category/{category_path}")
    suspend fun getSubCategories(@Path("category_path") categoryPath:String ):Response<SubCategoryResponse>
}
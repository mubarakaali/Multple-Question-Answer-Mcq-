package fivesol.networklibrary.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import fivesol.networklibrary.domain.models.sub_category.Mcq

@Dao
interface SubCategoryMcqDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertAllSubCategoryMcq(mcqList:List<Mcq>)
    @Query("SELECT * FROM mcq_computer where categoryId =:categoryId")
     fun getAllSubCategoryMcq(categoryId:Int): Flow<List<Mcq>>

}
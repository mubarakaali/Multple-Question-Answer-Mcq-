package fivesol.networklibrary.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fivesol.networklibrary.domain.models.main_category.Data
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMainCategory(mainData: List<Data>)
    @Query("SELECT * FROM MAIN_CATEGORY")
    fun getCategoryList():Flow<List<Data>>


}
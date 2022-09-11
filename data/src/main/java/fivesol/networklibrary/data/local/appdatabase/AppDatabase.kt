package fivesol.networklibrary.data.local.appdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fivesol.networklibrary.data.local.daos.CategoryDao
import fivesol.networklibrary.data.local.daos.SubCategoryMcqDao
import fivesol.networklibrary.data.utils.ApiConstants.DATABASE_NAME
import fivesol.networklibrary.domain.models.main_category.Data
import fivesol.networklibrary.domain.models.sub_category.Mcq

@Database(entities = [Data::class,Mcq::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun subCategoryMcq():SubCategoryMcqDao

    companion object {
//      for Singleton instantiation
        @Volatile private var instance: AppDatabase?=null
        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this){
                instance ?: buildDatabase(context)
            }
        }

        private fun buildDatabase(appContext:Context)=
            Room.databaseBuilder(appContext, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}
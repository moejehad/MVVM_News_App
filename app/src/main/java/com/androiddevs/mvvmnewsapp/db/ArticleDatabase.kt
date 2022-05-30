package com.androiddevs.mvvmnewsapp.db

import android.content.Context
import androidx.room.*
import com.androiddevs.mvvmnewsapp.models.Article

@Database(
    entities = [Article::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase(){

    abstract fun getArticleDao() : ArticleDao

    companion object {
        @Volatile
        private var instence : ArticleDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instence ?: synchronized(LOCK) {
            instence ?: createDatabase(context).also {
                instence = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()
    }

}
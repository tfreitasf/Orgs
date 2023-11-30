package br.com.povengenharia.orgs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.povengenharia.orgs.database.converter.Converters
import br.com.povengenharia.orgs.database.dao.ProductDao
import br.com.povengenharia.orgs.model.Product

@Database(entities = [Product::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile private var db: AppDatabase? = null
        fun getInstance(context: Context) : AppDatabase{
            return db ?: Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "orgs.db"
            ).build()
                .also {
                    db = it
                }

        }
    }
}
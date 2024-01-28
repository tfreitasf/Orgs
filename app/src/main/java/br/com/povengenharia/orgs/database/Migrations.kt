package br.com.povengenharia.orgs.database

import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `User` (
            `id` TEXT NOT NULL, 
            `name` TEXT NOT NULL, 
            `password` TEXT NOT NULL, PRIMARY KEY(`id`))
            """
        )

    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE Product ADD COLUMN 'userId' TEXT")
    }
}

class Migration_3_4 : AutoMigrationSpec {
    override fun onPostMigrate(db: SupportSQLiteDatabase) {
        db.execSQL("UPDATE User SET email = '' WHERE email IS NULL")

    }
}



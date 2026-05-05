package data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [DepositEntity::class], version = 1)
abstract class DepositDatabase : RoomDatabase() {
    abstract fun depositDao(): DepositDao

    companion object {
        @Volatile
        private var INSTANCE: DepositDatabase? = null

        fun getInstance(context: Context): DepositDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    DepositDatabase::class.java,
                    "deposit_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
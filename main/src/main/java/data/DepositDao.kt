package data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface DepositDao {
    @Insert
    suspend fun insert(calculation: DepositEntity): Long

    @Query("SELECT * FROM deposit_calculations ORDER BY date DESC")
    suspend fun getAllCalculations(): List<DepositEntity>

    @Query("SELECT * FROM deposit_calculations WHERE id = :id")
    suspend fun getCalculationById(id: Long): DepositEntity?
}
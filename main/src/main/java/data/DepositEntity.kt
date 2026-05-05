package data
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "deposit_calculations")
data class DepositEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: Long,  // Хранение timestamp
    val initialAmount: Double,
    val termMonths: Int,
    val interestRate: Double,
    val monthlyAddition: Double,
    val finalAmount: Double,
    val earnedInterest: Double
)
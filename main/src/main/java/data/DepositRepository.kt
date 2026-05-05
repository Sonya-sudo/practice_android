package data

class DepositRepository(private val depositDao: DepositDao) {

    suspend fun saveCalculation(calculation: DepositEntity): Result<Long> {
        return try {
            val id = depositDao.insert(calculation)
            Result.success(id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAllCalculations(): List<DepositEntity> {
        return try {
            depositDao.getAllCalculations()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getCalculationById(id: Long): DepositEntity? {
        return try {
            depositDao.getCalculationById(id)
        } catch (e: Exception) {
            null
        }
    }
}
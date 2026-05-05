package viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class DepositInputData(
    var initialAmount: Double? = null,
    var termMonths: Int? = null,
    var interestRate: Double? = null,
    var monthlyAddition: Double = 0.0
)

class SharedViewModel : ViewModel() {
    private val _depositData = MutableLiveData(DepositInputData())
    val depositData: LiveData<DepositInputData> = _depositData

    fun updateStep1Data(initialAmount: Double, termMonths: Int) {
        val current = _depositData.value ?: DepositInputData()
        current.initialAmount = initialAmount
        current.termMonths = termMonths
        _depositData.value = current
    }

    fun updateStep2Data(interestRate: Double, monthlyAddition: Double) {
        val current = _depositData.value ?: return
        current.interestRate = interestRate
        current.monthlyAddition = monthlyAddition
        _depositData.value = current
    }
}
package ae.ekar.public

import ae.ekar.model.ErrorInfo
import ae.ekar.model.IncrementThreadsRequest

interface EngineApiService {

    fun incrementThreads(incrementThreadsRequest: IncrementThreadsRequest): Unit

    fun updateCounter(counterValue: kotlin.Long): Unit
}

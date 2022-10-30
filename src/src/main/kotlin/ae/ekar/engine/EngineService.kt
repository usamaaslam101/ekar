package ae.ekar.engine

import ae.ekar.engine.model.Counter
import ae.ekar.engine.model.CounterOperations
import ae.ekar.model.IncrementThreadsRequest
import ae.ekar.public.EngineApiService
import ae.ekar.engine.model.CounterOperations.INCREMENT
import ae.ekar.engine.model.CounterOperations.DECREMENT
import ae.ekar.engine.model.CounterOperations.SET
import ae.ekar.filter.repository.CounterRepository
import org.springframework.stereotype.Service
import kotlin.system.measureTimeMillis

@Service
class EngineService(
  private val counterRepository: CounterRepository
) : EngineApiService {
  companion object {
    private var counter = 50
  }

  override fun incrementThreads(incrementThreadsRequest: IncrementThreadsRequest) {
    if (incrementThreadsRequest.incrementProducerCount > 0)
      createProducerThreads(incrementThreadsRequest.incrementProducerCount)

    if (incrementThreadsRequest.incrementConsumerCount > 0)
      createConsumerThreads(incrementThreadsRequest.incrementConsumerCount)
  }

  private fun createProducerThreads(incrementProducerCount: Long) {
    for (i in 1..incrementProducerCount) {
      Thread(Runnable {
        while (true) {
          Thread.sleep(2000)
          Thread.currentThread().name
          manipulateCounter(INCREMENT, 1, Thread.currentThread().name)
        }
      }).start()
    }
  }

  private fun createConsumerThreads(incrementConsumerCount: Long) {
    for (i in 1..incrementConsumerCount) {
      Thread(Runnable {
        while (true) {
          Thread.sleep(2000)
          Thread.currentThread().name
          manipulateCounter(DECREMENT, 1, Thread.currentThread().name)
        }
      }).start()
    }
  }

  @Synchronized
  private fun manipulateCounter(operation: CounterOperations, operationValue: Long, threadName: String) {
    var isValueUpdated = false
    when (operation) {
      INCREMENT -> {
        if (counter < 100) {
          counter += operationValue.toInt()
          println("In $threadName counter incremented to $counter")
          isValueUpdated = true
        }
      }
      DECREMENT -> {
        if (counter > 0) {
          counter -= operationValue.toInt()
          println("In $threadName counter decremented to $counter")
          isValueUpdated = true
        }
      }
      SET -> {
        counter = operationValue.toInt()
        println("Counter reset to $counter by $threadName")
      }
    }

    if ((counter == 0 || counter == 100) && operation != SET && isValueUpdated )
      counterRepository.save(
        Counter(
          value = counter
        )
      )

  }

  override fun updateCounter(counterValue: Long) {
    manipulateCounter(SET, counterValue, Thread.currentThread().name)
  }
}

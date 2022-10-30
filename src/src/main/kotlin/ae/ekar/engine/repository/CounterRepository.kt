package ae.ekar.filter.repository

import ae.ekar.engine.model.Counter
import ae.ekar.filter.model.RequestData
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CounterRepository : CrudRepository<Counter, Long>

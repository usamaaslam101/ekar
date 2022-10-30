package ae.ekar.filter.repository

import ae.ekar.filter.model.RequestData
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RequestDataRepository : CrudRepository<RequestData, Long>

package ae.ekar.filter.model

import ae.ekar.time.ClockHolder
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table("request_data")
data class RequestData(
  @Id
  val id: Long? = null,
  val url: String,
  val methodType: String,
  val createdTime: Instant = ClockHolder.CLOCK.instant(),
)

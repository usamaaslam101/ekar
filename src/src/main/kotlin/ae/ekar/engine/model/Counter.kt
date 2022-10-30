package ae.ekar.engine.model

import ae.ekar.time.ClockHolder
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant


@Table("counter_data")
data class Counter(
  @Id
  val id: Long? = null,
  val value: Int,
  val createdTime: Instant = ClockHolder.CLOCK.instant(),
)

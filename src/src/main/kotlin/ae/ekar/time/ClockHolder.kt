package ae.ekar.time

import java.time.Clock
import java.time.ZoneId

object ClockHolder {
  val CLOCK: Clock = object : Clock() {
    override fun withZone(zone: ZoneId?) = clockImpl.withZone(zone)
    override fun getZone() = clockImpl.zone
    override fun instant() = clockImpl.instant()
  }
  var timeZone: ZoneId = ZoneId.of("UTC").normalized()
  var clockImpl: Clock = Clock.system(timeZone)
}

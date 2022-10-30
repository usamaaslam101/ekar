package ae.ekar

import ae.ekar.time.ClockHolder.CLOCK
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class ProjectApplication{
	@Bean
	fun clock() = CLOCK
}

fun main(args: Array<String>) {
	runApplication<ProjectApplication>(*args)
}

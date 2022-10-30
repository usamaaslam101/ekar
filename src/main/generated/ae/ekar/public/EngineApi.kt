package ae.ekar.public

import ae.ekar.model.ErrorInfo
import ae.ekar.model.IncrementThreadsRequest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.*
import org.springframework.validation.annotation.Validated
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.beans.factory.annotation.Autowired

import javax.validation.Valid
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

import kotlin.collections.List
import kotlin.collections.Map

@RestController
@Validated
@RequestMapping("\${api.base-path:/api/v1}")
class EngineApiController(@Autowired(required = true) val service: EngineApiService) {


    @PostMapping(
        value = ["/threads"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun incrementThreads( @Valid @RequestBody incrementThreadsRequest: IncrementThreadsRequest
): ResponseEntity<Unit> {
        return ResponseEntity(service.incrementThreads(incrementThreadsRequest), HttpStatus.valueOf(201))
    }


    @PutMapping(
        value = ["/counter/{counterValue}"]
    )
    fun updateCounter(@Min(0L) @Max(100L)  @PathVariable("counterValue") counterValue: kotlin.Long
): ResponseEntity<Unit> {
        return ResponseEntity(service.updateCounter(counterValue), HttpStatus.valueOf(200))
    }
}

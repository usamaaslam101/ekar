package ae.ekar.exception

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class ApiExceptionHandler {

  @ExceptionHandler(ApiException::class)
  fun handleApiException(exception: ApiException, request: WebRequest): ResponseEntity<ApiError> {
    val apiError = exception.toApiError()

    return ResponseEntity(apiError, HttpStatus.valueOf(exception.code))
  }
}

fun ApiException.toApiError(): ApiError {
  return ApiError(this.javaClass.simpleName, this.message ?: this.localizedMessage ?: "")
}

data class ApiError(
  val name: String,
  val message: String
)

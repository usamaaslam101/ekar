package ae.ekar.filter

import ae.ekar.filter.model.RequestData
import ae.ekar.filter.repository.RequestDataRepository
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

@Component
class RequestLoggingFilter(
  private val requestDataRepository: RequestDataRepository
) : Filter {
  @Throws(IOException::class, ServletException::class)
  override fun doFilter(
    request: ServletRequest,
    response: ServletResponse,
    chain: FilterChain
  ) {
    val req = request as HttpServletRequest
    requestDataRepository.save(
      RequestData(
        methodType = req.method,
        url = req.requestURI
      )
    )
    chain.doFilter(req, response)
  }
}

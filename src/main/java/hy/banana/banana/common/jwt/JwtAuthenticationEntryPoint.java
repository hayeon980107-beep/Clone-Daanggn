package hy.banana.banana.common.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import hy.banana.banana.common.exception.ErrorCode;
import hy.banana.banana.common.response.ApiResponse;
import hy.banana.banana.common.response.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");

        ApiResponse<?> apiResponse = ApiResponse.fail(ErrorResponse.of(ErrorCode.UNAUTHORIZED));

        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }
}

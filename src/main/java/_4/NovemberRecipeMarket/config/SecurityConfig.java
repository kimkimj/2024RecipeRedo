package _4.NovemberRecipeMarket.config;

import _4.NovemberRecipeMarket.domain.dto.Response;
import _4.NovemberRecipeMarket.exception.ErrorCode;
import _4.NovemberRecipeMarket.security.JwtExceptionFilter;
import _4.NovemberRecipeMarket.security.JwtTokenFilter;
import _4.NovemberRecipeMarket.security.JwtTokenUtils;
import _4.NovemberRecipeMarket.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Value("${jwt.token.secret}")
    private String secretKey;

    private final JwtTokenUtils jwtTokenUtils;
    private final UserService userService;

    private final String[] POST_PERMIT = {
            "/api/v1/users/join",
            "/api/v1/users/login",

            // ui
            "/login",
            "/join",
            "/users/join",
            "/users/login"
    };

    private final String[] GET_AUTHENTICATED ={
            "/users/my",
            "users/my/**",
            "/users/logout"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic(http -> http.disable()) // Disable HTTP Basic Authentication
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .cors(Customizer.withDefaults()); // Enable CORS with default configuration (modify as needed)

        httpSecurity
                .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.POST, POST_PERMIT).permitAll() // Public access to join and login
                .requestMatchers(HttpMethod.GET, GET_AUTHENTICATED).authenticated() // Require authentication for specific GET endpoints
                .requestMatchers(HttpMethod.GET).permitAll()
                .requestMatchers(HttpMethod.POST).authenticated()
                .requestMatchers(HttpMethod.PUT).authenticated()
                .requestMatchers(HttpMethod.PATCH).authenticated()
                .requestMatchers(HttpMethod.DELETE).authenticated());// Secure all other endpoints

        httpSecurity
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity
                .addFilterBefore(new JwtTokenFilter(secretKey, jwtTokenUtils, userService),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtExceptionFilter(), JwtTokenFilter.class);

        httpSecurity.exceptionHandling(ex -> ex
                .authenticationEntryPoint((request, response, authException) -> {
                    // TODO: Might need to redirect
                    //response.sendRedirect("/login");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Return 401 Unauthorized
                    makeErrorResponse(response, ErrorCode.INVALID_PERMISSION);
                })

                // 인가 실패시 ROLE_FORBIDDEN 에러 발생
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        response.sendRedirect("/error/403");
                        makeErrorResponse(response, ErrorCode.ROLE_FORBIDDEN);
                    }
                })
        );
        return httpSecurity.build();
    }

    // Security Filter Chain에서 발생하는 Exception은 Exception Manager 까지 가지 않기 때문에 여기서 직접 처리한다
    public void makeErrorResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), Response.error(ErrorCode.INVALID_PERMISSION.getMessage()));
    }

}

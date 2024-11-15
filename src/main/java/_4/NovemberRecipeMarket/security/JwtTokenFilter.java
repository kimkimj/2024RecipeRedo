package _4.NovemberRecipeMarket.security;

import _4.NovemberRecipeMarket.domain.dto.seller.SellerResponse;
import _4.NovemberRecipeMarket.domain.dto.user.UserResponse;
import _4.NovemberRecipeMarket.service.SellerService;
import _4.NovemberRecipeMarket.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final String secretKey;
    private final JwtTokenUtils jwtTokenUtils;
    private final UserService userService;
    private final SellerService sellerService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        Cookie[] cookies = request.getCookies();

        try {
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                    log.info("token retrieved");
                }
            }
        } catch (Exception e) {
            log.error("error found");
            filterChain.doFilter(request, response);
            return;
        }

        if (token == null || token.equals("deleted")) {
            filterChain.doFilter(request, response);
            log.info("token deleted");
            return;
        }

        log.info("token valid");
        String username = jwtTokenUtils.getUsername(token, secretKey);
        String userRole = jwtTokenUtils.getUserRole(token, secretKey);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, null, List.of(new SimpleGrantedAuthority(userRole)));

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}

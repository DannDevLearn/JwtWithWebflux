package com.example.jjwt.webflux.config.filter;

import com.example.jjwt.webflux.config.security.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JwtAuthenticationFilter implements WebFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> filter(@NonNull ServerWebExchange exchange, @NonNull WebFilterChain chain) {
        String token = extractTokenFromHeader(exchange);
        String path = exchange.getRequest().getPath().toString();

        if (path.startsWith("/auth") || path.equals("/hello")) {
            return chain.filter(exchange); // Ignorar el filtro para estos endpoints
        }
        Claims claims = jwtUtil.getPayload(token);
//        List<String> roles = claims.get("role", List.class);

        if (token != null && jwtUtil.validateToken(token)) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    claims.getSubject(),
                    null,
                    null
//                    roles.stream().map(SimpleGrantedAuthority::new).toList()
            );
            return chain.filter(exchange)
                    .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
        }
        return chain.filter(exchange);
    }

    private String extractTokenFromHeader(ServerWebExchange exchange){
        String bearerToken = exchange.getRequest().getHeaders().getFirst("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }

}

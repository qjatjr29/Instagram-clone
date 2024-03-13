package com.beomstagram.gateway.filter;

import com.beomstagram.gateway.filter.AuthorizationFilter.Config;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthorizationFilter extends AbstractGatewayFilterFactory<Config> {

    private final Key key;

    public AuthorizationFilter(@Value("${token.secret}") String secretToken){
        super(Config.class);
        byte[] keyBytes = Decoders.BASE64.decode(secretToken);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
//                request.mutate().header("userId", "").build();

                return chain.filter(exchange);
            }

            String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String jwt = authorizationHeader.replace("Bearer", "");

            if(!isJwtValid(jwt)) {
                return onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED);
            }

            Long userId = getUserId(jwt);
            request.mutate().header("userId", String.valueOf(userId)).build();

            return chain.filter(exchange);
        });
    }

    private boolean isJwtValid(String jwt) {
        boolean returnValue = true;
        String subject = null;

        try {
            subject = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt).getBody()
                    .getSubject();
            log.info(key.getFormat());

        } catch (Exception ex) {
            returnValue = false;
        }

        if(subject == null || subject.isEmpty()) returnValue = false;
        return returnValue;
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {

        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);

        log.error(err);
        return response.setComplete();
    }

    private Long getUserId(String jwtToken) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
        return claims.get("userId", Long.class);
    }

//    private boolean validateJwtToken(String jwtToken) {
//        try {
//            Jwts.parser().setSigningKey(key).parseClaimsJws(jwtToken);
//            return true;
//        } catch (MalformedJwtException ex) {
//            // Invalid JWT token: 유효하지 않은 JWT 토큰일 때 발생하는 예외
//        } catch (ExpiredJwtException ex) {
//            // Expired JWT token: 토큰의 유효기간이 만료된 경우 발생하는 예외
//        } catch (UnsupportedJwtException ex) {
//            // Unsupported JWT token: 지원하지 않는 JWT 토큰일 때 발생하는 예외
//        } catch (IllegalArgumentException ex) {
//            // JWT claims string is empty: JWT 토큰이 비어있을 때 발생하는 예외
//        }
//        return false;
//    }

    public static class Config {
    }
}

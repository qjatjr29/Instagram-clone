package com.beomstagram.gateway.filter;

import com.beomstagram.gateway.filter.AuthorizationPermissionFilter.Config;
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
public class AuthorizationPermissionFilter extends AbstractGatewayFilterFactory<Config> {

    private final Key key;

    public AuthorizationPermissionFilter(@Value("${token.secret}") String secretToken){
        super(AuthorizationPermissionFilter.Config.class);
        byte[] keyBytes = Decoders.BASE64.decode(secretToken);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "no Authorization header", HttpStatus.UNAUTHORIZED);
            }

            String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String jwt = authorizationHeader.replace("Bearer", "");

            if(!isJwtValid(jwt)) {
                return onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED);
            }

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

    public static class Config {
    }
}

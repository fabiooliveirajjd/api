package com.fabio.api;

import com.fabio.api.dto.UsuarioLoginDto;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.function.Consumer;

public class JwtAuthentication {

    public static Consumer<HttpHeaders> getHeaderAuthorizathion(WebTestClient client, String username, String password) {
        String token = client
                .post()
                .uri("/api/v1/auth")
                .bodyValue(new UsuarioLoginDto(username, password))
                .exchange()
                .expectStatus().isOk()
                .expectBody(JetToken.class)
                .returnResult().getResponseBody().getToken();
        return headers -> headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    }
}

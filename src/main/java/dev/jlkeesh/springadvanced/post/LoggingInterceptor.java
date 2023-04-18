package dev.jlkeesh.springadvanced.post;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;


@Slf4j
@Component
public class LoggingInterceptor implements ClientHttpRequestInterceptor {


    @Override
    public @NotNull ClientHttpResponse intercept(@NotNull HttpRequest req, byte @NotNull [] reqBody,
                                                 ClientHttpRequestExecution ex) throws IOException {
        log.debug("Request body: {}", new String(reqBody, StandardCharsets.UTF_8));
        ClientHttpResponse response = ex.execute(req, reqBody);
        InputStreamReader isr = new InputStreamReader(response.getBody(), StandardCharsets.UTF_8);
        String body = new BufferedReader(isr).lines().collect(Collectors.joining("\n"));
        log.debug("Response body: {}", body);
        return response;
    }
}
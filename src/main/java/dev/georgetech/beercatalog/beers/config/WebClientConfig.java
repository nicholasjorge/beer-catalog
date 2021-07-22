package dev.georgetech.beercatalog.beers.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration(proxyBeanMethods = false)
public class WebClientConfig {

    private static final String PUNK_API_V2_BASE_URL = "https://api.punkapi.com/v2";
    private static final int TIMEOUT_VALUE = 5000;

    @Bean
    WebClient simpleWebClient() {
        return WebClient.create(PUNK_API_V2_BASE_URL);
    }

    @Primary
    @Bean
    WebClient customWebClient(WebClient.Builder builder, HttpClient httpClient) {
        return builder
                .baseUrl(PUNK_API_V2_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    @Bean
    HttpClient httpClient() {
        return HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT_VALUE)
                .responseTimeout(Duration.ofMillis(TIMEOUT_VALUE))
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(TIMEOUT_VALUE, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(TIMEOUT_VALUE, TimeUnit.MILLISECONDS)));

    }

}

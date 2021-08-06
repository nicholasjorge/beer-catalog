package dev.georgetech.beercatalog.beers.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
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
        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(PUNK_API_V2_BASE_URL);
        uriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT);

        Jackson2ObjectMapperBuilder objectMapperBuilder = new Jackson2ObjectMapperBuilder();
        objectMapperBuilder.propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        ObjectMapper objectMapper = objectMapperBuilder.build();

        return builder
                .baseUrl(PUNK_API_V2_BASE_URL)
                .uriBuilderFactory(uriBuilderFactory)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .codecs(clientCodecConfigurer -> clientCodecConfigurer
                        .defaultCodecs()
                        .jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper)))
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

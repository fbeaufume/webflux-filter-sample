package com.adeliosys.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.UUID;

@Component
public class LogFilter implements WebFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String traceId = UUID.randomUUID().toString();
        long startTime = System.currentTimeMillis();
        String path = exchange.getRequest().getURI().getPath();
        LOGGER.info("Request [{}] started, traceId [{}]", path, traceId);

        return chain.filter(exchange)
                .doAfterSuccessOrError((r, t) ->
                        LOGGER.info("Request [{}] completed, statusCode [{}], time [{}], traceId [{}]",
                                path,
                                exchange.getResponse().getStatusCode(),
                                System.currentTimeMillis() - startTime,
                                traceId))
                .subscriberContext(Context.of(String.class, traceId));
    }
}

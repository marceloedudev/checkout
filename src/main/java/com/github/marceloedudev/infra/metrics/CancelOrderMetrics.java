package com.github.marceloedudev.infra.metrics;

import io.micrometer.core.instrument.MeterRegistry;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CancelOrderMetrics {

    private final MeterRegistry registry;

    public CancelOrderMetrics(MeterRegistry registry) {
        this.registry = registry;
    }

    public void addCountSuccess() {
        registry.counter("cancel_order_success.count").increment();
    }

}

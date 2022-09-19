package com.github.marceloedudev.infra.metrics;

import io.micrometer.core.instrument.MeterRegistry;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlaceOrderMetrics  {

    private final MeterRegistry registry;

    public PlaceOrderMetrics(MeterRegistry registry) {
        this.registry = registry;
    }

    public void addCountSuccess() {
        registry.counter("place_order_success").increment();
    }

}

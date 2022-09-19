package com.github.marceloedudev.pkg.tracing;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.tag.Tags;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;

@ApplicationScoped
public class OpenTracing implements Tracing {

    @Inject
    Tracer tracer;

    Span span;

    @Override
    public void startSpanFromContext(String operationName, String componentName) {
        span = tracer.buildSpan(operationName)
                .withTag(Tags.COMPONENT.getKey(), componentName)
                .start();
        span.log("start");
    }

    @Override
    public void finish() {
        span.finish();
    }

    @Override
    public void logFields(Map<String, ?> var1) {
        span.log(var1);
    }

    @Override
    public void logFields(long var1, Map<String, ?> var3) {
        span.log(var1, var3);
    }

    @Override
    public void logFields(String var1) {
        span.log(var1);
    }

    @Override
    public void logFields(long var1, String var3) {
        span.log(var1, var3);
    }

}

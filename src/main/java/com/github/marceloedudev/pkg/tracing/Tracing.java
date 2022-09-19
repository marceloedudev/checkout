package com.github.marceloedudev.pkg.tracing;

import java.util.Map;

public interface Tracing {
    public void startSpanFromContext(String operationName, String componentName);

    public void finish();

    public void logFields(Map<String, ?> var1);

    public void logFields(long var1, Map<String, ?> var3);

    public void logFields(String var1);

    public void logFields(long var1, String var3);
}

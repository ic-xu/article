/**
 * Copyright 2019 bejson.com
 */
package com.admin.cx.pojo;

/**
 * Auto-generated: 2019-07-09 18:34:38
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class TBean {

    private String type;
    private String name;
    private String group;
    private long currentTime;
    private boolean isCircuitBreakerOpen;
    private int errorPercentage;
    private int errorCount;
    private int requestCount;
    private int rollingCountBadRequests;
    private int rollingCountCollapsedRequests;
    private int rollingCountEmit;
    private int rollingCountExceptionsThrown;
    private int rollingCountFailure;
    private int rollingCountFallbackEmit;
    private int rollingCountFallbackFailure;
    private int rollingCountFallbackMissing;
    private int rollingCountFallbackRejection;
    private int rollingCountFallbackSuccess;
    private int rollingCountResponsesFromCache;
    private int rollingCountSemaphoreRejected;
    private int rollingCountShortCircuited;
    private int rollingCountSuccess;
    private int rollingCountThreadPoolRejected;
    private int rollingCountTimeout;
    private int currentConcurrentExecutionCount;
    private int rollingMaxConcurrentExecutionCount;
    private int latencyExecute_mean;
    private int latencyTotal_mean;
    private int propertyValue_circuitBreakerRequestVolumeThreshold;
    private int propertyValue_circuitBreakerSleepWindowInMilliseconds;
    private int propertyValue_circuitBreakerErrorThresholdPercentage;
    private boolean propertyValue_circuitBreakerForceOpen;
    private boolean propertyValue_circuitBreakerForceClosed;
    private boolean propertyValue_circuitBreakerEnabled;
    private String propertyValue_executionIsolationStrategy;
    private int propertyValue_executionIsolationThreadTimeoutInMilliseconds;
    private int propertyValue_executionTimeoutInMilliseconds;
    private boolean propertyValue_executionIsolationThreadInterruptOnTimeout;
    private String propertyValue_executionIsolationThreadPoolKeyOverride;
    private int propertyValue_executionIsolationSemaphoreMaxConcurrentRequests;
    private int propertyValue_fallbackIsolationSemaphoreMaxConcurrentRequests;
    private int propertyValue_metricsRollingStatisticalWindowInMilliseconds;
    private boolean propertyValue_requestCacheEnabled;
    private boolean propertyValue_requestLogEnabled;
    private int reportingHosts;
    private String threadPool;
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setGroup(String group) {
        this.group = group;
    }
    public String getGroup() {
        return group;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }
    public long getCurrentTime() {
        return currentTime;
    }

    public void setIsCircuitBreakerOpen(boolean isCircuitBreakerOpen) {
        this.isCircuitBreakerOpen = isCircuitBreakerOpen;
    }
    public boolean getIsCircuitBreakerOpen() {
        return isCircuitBreakerOpen;
    }

    public void setErrorPercentage(int errorPercentage) {
        this.errorPercentage = errorPercentage;
    }
    public int getErrorPercentage() {
        return errorPercentage;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }
    public int getErrorCount() {
        return errorCount;
    }

    public void setRequestCount(int requestCount) {
        this.requestCount = requestCount;
    }
    public int getRequestCount() {
        return requestCount;
    }

    public void setRollingCountBadRequests(int rollingCountBadRequests) {
        this.rollingCountBadRequests = rollingCountBadRequests;
    }
    public int getRollingCountBadRequests() {
        return rollingCountBadRequests;
    }

    public void setRollingCountCollapsedRequests(int rollingCountCollapsedRequests) {
        this.rollingCountCollapsedRequests = rollingCountCollapsedRequests;
    }
    public int getRollingCountCollapsedRequests() {
        return rollingCountCollapsedRequests;
    }

    public void setRollingCountEmit(int rollingCountEmit) {
        this.rollingCountEmit = rollingCountEmit;
    }
    public int getRollingCountEmit() {
        return rollingCountEmit;
    }

    public void setRollingCountExceptionsThrown(int rollingCountExceptionsThrown) {
        this.rollingCountExceptionsThrown = rollingCountExceptionsThrown;
    }
    public int getRollingCountExceptionsThrown() {
        return rollingCountExceptionsThrown;
    }

    public void setRollingCountFailure(int rollingCountFailure) {
        this.rollingCountFailure = rollingCountFailure;
    }
    public int getRollingCountFailure() {
        return rollingCountFailure;
    }

    public void setRollingCountFallbackEmit(int rollingCountFallbackEmit) {
        this.rollingCountFallbackEmit = rollingCountFallbackEmit;
    }
    public int getRollingCountFallbackEmit() {
        return rollingCountFallbackEmit;
    }

    public void setRollingCountFallbackFailure(int rollingCountFallbackFailure) {
        this.rollingCountFallbackFailure = rollingCountFallbackFailure;
    }
    public int getRollingCountFallbackFailure() {
        return rollingCountFallbackFailure;
    }

    public void setRollingCountFallbackMissing(int rollingCountFallbackMissing) {
        this.rollingCountFallbackMissing = rollingCountFallbackMissing;
    }
    public int getRollingCountFallbackMissing() {
        return rollingCountFallbackMissing;
    }

    public void setRollingCountFallbackRejection(int rollingCountFallbackRejection) {
        this.rollingCountFallbackRejection = rollingCountFallbackRejection;
    }
    public int getRollingCountFallbackRejection() {
        return rollingCountFallbackRejection;
    }

    public void setRollingCountFallbackSuccess(int rollingCountFallbackSuccess) {
        this.rollingCountFallbackSuccess = rollingCountFallbackSuccess;
    }
    public int getRollingCountFallbackSuccess() {
        return rollingCountFallbackSuccess;
    }

    public void setRollingCountResponsesFromCache(int rollingCountResponsesFromCache) {
        this.rollingCountResponsesFromCache = rollingCountResponsesFromCache;
    }
    public int getRollingCountResponsesFromCache() {
        return rollingCountResponsesFromCache;
    }

    public void setRollingCountSemaphoreRejected(int rollingCountSemaphoreRejected) {
        this.rollingCountSemaphoreRejected = rollingCountSemaphoreRejected;
    }
    public int getRollingCountSemaphoreRejected() {
        return rollingCountSemaphoreRejected;
    }

    public void setRollingCountShortCircuited(int rollingCountShortCircuited) {
        this.rollingCountShortCircuited = rollingCountShortCircuited;
    }
    public int getRollingCountShortCircuited() {
        return rollingCountShortCircuited;
    }

    public void setRollingCountSuccess(int rollingCountSuccess) {
        this.rollingCountSuccess = rollingCountSuccess;
    }
    public int getRollingCountSuccess() {
        return rollingCountSuccess;
    }

    public void setRollingCountThreadPoolRejected(int rollingCountThreadPoolRejected) {
        this.rollingCountThreadPoolRejected = rollingCountThreadPoolRejected;
    }
    public int getRollingCountThreadPoolRejected() {
        return rollingCountThreadPoolRejected;
    }

    public void setRollingCountTimeout(int rollingCountTimeout) {
        this.rollingCountTimeout = rollingCountTimeout;
    }
    public int getRollingCountTimeout() {
        return rollingCountTimeout;
    }

    public void setCurrentConcurrentExecutionCount(int currentConcurrentExecutionCount) {
        this.currentConcurrentExecutionCount = currentConcurrentExecutionCount;
    }
    public int getCurrentConcurrentExecutionCount() {
        return currentConcurrentExecutionCount;
    }

    public void setRollingMaxConcurrentExecutionCount(int rollingMaxConcurrentExecutionCount) {
        this.rollingMaxConcurrentExecutionCount = rollingMaxConcurrentExecutionCount;
    }
    public int getRollingMaxConcurrentExecutionCount() {
        return rollingMaxConcurrentExecutionCount;
    }

    public void setLatencyExecute_mean(int latencyExecute_mean) {
        this.latencyExecute_mean = latencyExecute_mean;
    }
    public int getLatencyExecute_mean() {
        return latencyExecute_mean;
    }


    public void setLatencyTotal_mean(int latencyTotal_mean) {
        this.latencyTotal_mean = latencyTotal_mean;
    }
    public int getLatencyTotal_mean() {
        return latencyTotal_mean;
    }


    public void setPropertyValue_circuitBreakerRequestVolumeThreshold(int propertyValue_circuitBreakerRequestVolumeThreshold) {
        this.propertyValue_circuitBreakerRequestVolumeThreshold = propertyValue_circuitBreakerRequestVolumeThreshold;
    }
    public int getPropertyValue_circuitBreakerRequestVolumeThreshold() {
        return propertyValue_circuitBreakerRequestVolumeThreshold;
    }

    public void setPropertyValue_circuitBreakerSleepWindowInMilliseconds(int propertyValue_circuitBreakerSleepWindowInMilliseconds) {
        this.propertyValue_circuitBreakerSleepWindowInMilliseconds = propertyValue_circuitBreakerSleepWindowInMilliseconds;
    }
    public int getPropertyValue_circuitBreakerSleepWindowInMilliseconds() {
        return propertyValue_circuitBreakerSleepWindowInMilliseconds;
    }

    public void setPropertyValue_circuitBreakerErrorThresholdPercentage(int propertyValue_circuitBreakerErrorThresholdPercentage) {
        this.propertyValue_circuitBreakerErrorThresholdPercentage = propertyValue_circuitBreakerErrorThresholdPercentage;
    }
    public int getPropertyValue_circuitBreakerErrorThresholdPercentage() {
        return propertyValue_circuitBreakerErrorThresholdPercentage;
    }

    public void setPropertyValue_circuitBreakerForceOpen(boolean propertyValue_circuitBreakerForceOpen) {
        this.propertyValue_circuitBreakerForceOpen = propertyValue_circuitBreakerForceOpen;
    }
    public boolean getPropertyValue_circuitBreakerForceOpen() {
        return propertyValue_circuitBreakerForceOpen;
    }

    public void setPropertyValue_circuitBreakerForceClosed(boolean propertyValue_circuitBreakerForceClosed) {
        this.propertyValue_circuitBreakerForceClosed = propertyValue_circuitBreakerForceClosed;
    }
    public boolean getPropertyValue_circuitBreakerForceClosed() {
        return propertyValue_circuitBreakerForceClosed;
    }

    public void setPropertyValue_circuitBreakerEnabled(boolean propertyValue_circuitBreakerEnabled) {
        this.propertyValue_circuitBreakerEnabled = propertyValue_circuitBreakerEnabled;
    }
    public boolean getPropertyValue_circuitBreakerEnabled() {
        return propertyValue_circuitBreakerEnabled;
    }

    public void setPropertyValue_executionIsolationStrategy(String propertyValue_executionIsolationStrategy) {
        this.propertyValue_executionIsolationStrategy = propertyValue_executionIsolationStrategy;
    }
    public String getPropertyValue_executionIsolationStrategy() {
        return propertyValue_executionIsolationStrategy;
    }

    public void setPropertyValue_executionIsolationThreadTimeoutInMilliseconds(int propertyValue_executionIsolationThreadTimeoutInMilliseconds) {
        this.propertyValue_executionIsolationThreadTimeoutInMilliseconds = propertyValue_executionIsolationThreadTimeoutInMilliseconds;
    }
    public int getPropertyValue_executionIsolationThreadTimeoutInMilliseconds() {
        return propertyValue_executionIsolationThreadTimeoutInMilliseconds;
    }

    public void setPropertyValue_executionTimeoutInMilliseconds(int propertyValue_executionTimeoutInMilliseconds) {
        this.propertyValue_executionTimeoutInMilliseconds = propertyValue_executionTimeoutInMilliseconds;
    }
    public int getPropertyValue_executionTimeoutInMilliseconds() {
        return propertyValue_executionTimeoutInMilliseconds;
    }

    public void setPropertyValue_executionIsolationThreadInterruptOnTimeout(boolean propertyValue_executionIsolationThreadInterruptOnTimeout) {
        this.propertyValue_executionIsolationThreadInterruptOnTimeout = propertyValue_executionIsolationThreadInterruptOnTimeout;
    }
    public boolean getPropertyValue_executionIsolationThreadInterruptOnTimeout() {
        return propertyValue_executionIsolationThreadInterruptOnTimeout;
    }

    public void setPropertyValue_executionIsolationThreadPoolKeyOverride(String propertyValue_executionIsolationThreadPoolKeyOverride) {
        this.propertyValue_executionIsolationThreadPoolKeyOverride = propertyValue_executionIsolationThreadPoolKeyOverride;
    }
    public String getPropertyValue_executionIsolationThreadPoolKeyOverride() {
        return propertyValue_executionIsolationThreadPoolKeyOverride;
    }

    public void setPropertyValue_executionIsolationSemaphoreMaxConcurrentRequests(int propertyValue_executionIsolationSemaphoreMaxConcurrentRequests) {
        this.propertyValue_executionIsolationSemaphoreMaxConcurrentRequests = propertyValue_executionIsolationSemaphoreMaxConcurrentRequests;
    }
    public int getPropertyValue_executionIsolationSemaphoreMaxConcurrentRequests() {
        return propertyValue_executionIsolationSemaphoreMaxConcurrentRequests;
    }

    public void setPropertyValue_fallbackIsolationSemaphoreMaxConcurrentRequests(int propertyValue_fallbackIsolationSemaphoreMaxConcurrentRequests) {
        this.propertyValue_fallbackIsolationSemaphoreMaxConcurrentRequests = propertyValue_fallbackIsolationSemaphoreMaxConcurrentRequests;
    }
    public int getPropertyValue_fallbackIsolationSemaphoreMaxConcurrentRequests() {
        return propertyValue_fallbackIsolationSemaphoreMaxConcurrentRequests;
    }

    public void setPropertyValue_metricsRollingStatisticalWindowInMilliseconds(int propertyValue_metricsRollingStatisticalWindowInMilliseconds) {
        this.propertyValue_metricsRollingStatisticalWindowInMilliseconds = propertyValue_metricsRollingStatisticalWindowInMilliseconds;
    }
    public int getPropertyValue_metricsRollingStatisticalWindowInMilliseconds() {
        return propertyValue_metricsRollingStatisticalWindowInMilliseconds;
    }

    public void setPropertyValue_requestCacheEnabled(boolean propertyValue_requestCacheEnabled) {
        this.propertyValue_requestCacheEnabled = propertyValue_requestCacheEnabled;
    }
    public boolean getPropertyValue_requestCacheEnabled() {
        return propertyValue_requestCacheEnabled;
    }

    public void setPropertyValue_requestLogEnabled(boolean propertyValue_requestLogEnabled) {
        this.propertyValue_requestLogEnabled = propertyValue_requestLogEnabled;
    }
    public boolean getPropertyValue_requestLogEnabled() {
        return propertyValue_requestLogEnabled;
    }

    public void setReportingHosts(int reportingHosts) {
        this.reportingHosts = reportingHosts;
    }
    public int getReportingHosts() {
        return reportingHosts;
    }

    public void setThreadPool(String threadPool) {
        this.threadPool = threadPool;
    }
    public String getThreadPool() {
        return threadPool;
    }

}
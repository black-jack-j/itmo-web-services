package org.blackjackj.wslab.lab4.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ThrottlingExecutionService {

    @Value("${wslab.throttling.capacity}")
    private int capacity;

    private final AtomicInteger size = new AtomicInteger(0);

    public CompletableFuture<?> submit(Runnable runnable) throws IllegalStateException {
        if (size.getAndIncrement() >= capacity) {
            size.decrementAndGet();
            throw new IllegalStateException("too much requests");
        }
        return CompletableFuture.runAsync(runnable).whenComplete((aVoid, err) -> {
            size.decrementAndGet();
        });
    }

    public <T> CompletableFuture<T> submit(Supplier<T> supplier) throws IllegalStateException {
        if (size.getAndIncrement() >= capacity) {
            size.decrementAndGet();
            throw new IllegalStateException("too much requests");
        }
        return CompletableFuture.supplyAsync(supplier).whenComplete((T res, Throwable err) -> {
            size.decrementAndGet();
        });
    }

}

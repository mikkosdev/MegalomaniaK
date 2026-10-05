package org.mikkosdev.megalomaniak.util;

import org.mikkosdev.megalomaniak.core.AbstractActor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ConcurrencyUtils {

    // This somewhat resembles the C# await syntax
    public static Object await(CompletableFuture<AbstractActor> future) {
        Object obj = null;
        try {
            obj = future.get();
        } catch (InterruptedException | ExecutionException e) {
            // Re-throw as unchecked exception
            throw new RuntimeException(e);
        }

        return obj;
    }
}

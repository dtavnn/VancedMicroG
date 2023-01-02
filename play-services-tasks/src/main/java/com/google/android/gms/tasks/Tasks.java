/*
 * SPDX-FileCopyrightText: 2016 microG Project Team
 * SPDX-License-Identifier: Apache-2.0 AND CC-BY-4.0
 * Notice: Portions of this file are reproduced from work created and shared by Google and used
 *         according to terms described in the Creative Commons 4.0 Attribution License.
 *         See https://developers.google.com/readme/policies for details.
 */

package com.google.android.gms.tasks;

import org.microg.gms.common.PublicApi;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/**
 * {@link Task} utility methods.
 */
@PublicApi
public final class Tasks {

    /**
     * Returns a {@link Task} that will be completed with the result of the specified {@code Callable}.
     * <p/>
     * If a non-{@link Exception} throwable is thrown in the callable, the {@link Task} will be failed with a
     * {@link RuntimeException} whose cause is the original throwable.
     * <p/>
     * The {@code Callable} will be called on the main application thread.
     *
     * @deprecated Use {@link TaskCompletionSource} instead, which allows the caller to manage their own Executor.
     */
    @Deprecated
    public static <TResult> Task<TResult> call(Callable<TResult> callable) {
        return call(TaskExecutors.MAIN_THREAD, callable);
    }

    /**
     * Returns a {@link Task} that will be completed with the result of the specified {@code Callable}.
     * <p/>
     * If a non-{@link Exception} throwable is thrown in the callable, the {@link Task} will be failed with a
     * {@link RuntimeException} whose cause is the original throwable.
     *
     * @param executor the Executor to use to call the {@code Callable}
     * @deprecated Use {@link TaskCompletionSource} instead, which allows the caller to manage their own Executor.
     */
    @Deprecated
    public static <TResult> Task<TResult> call(Executor executor, Callable<TResult> callable) {
        if (executor == null) throw new IllegalArgumentException("Executor must not be null");
        if (callable == null) throw new IllegalArgumentException("Callable must not be null");
        TaskCompletionSource<TResult> taskCompletionSource = new TaskCompletionSource<>();
        executor.execute(() -> {
            try {
                taskCompletionSource.setResult(callable.call());
            } catch (Exception e) {
                taskCompletionSource.trySetException(e);
            } catch (Throwable t) {
                taskCompletionSource.trySetException(new RuntimeException(t));
            }
        });
        return taskCompletionSource.getTask();
    }

}

/*
 * Copyright (C) 2013-2017 microG Project Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.gms.common.data;

import androidx.annotation.NonNull;

import com.google.android.gms.common.api.Releasable;

import org.microg.gms.common.PublicApi;

import java.util.Iterator;

/**
 * TODO
 */
@PublicApi
public abstract class DataBuffer<T> implements Releasable, Iterable<T> {

    @PublicApi(exclude = true)
    public DataBuffer() {
    }

    /**
     * @deprecated use {@link #release()} instead
     */
    @Deprecated
    public final void close() {
        release();
    }

    /**
     * @deprecated {@link #release()} is idempotent, and so is safe to call multiple times
     */
    @Deprecated
    public boolean isClosed() {
        return false;
    }

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return null;
    }

    /**
     * Releases resources used by the buffer. This method is idempotent.
     */
    @Override
    public void release() {

    }

}

/*
 * Copyright 2015 Al-Kathiri Khalid www.alkathirikhalid.com
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
package com.alkathirikhalid.connection.call;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * <p>Background task, thread provider</p>
 *
 * @author alkathirikhalid
 * @version 2.1.0
 */
abstract class BackgroundTask extends Thread {
    private final Task task;

    /**
     * @param task the task call back
     */
    protected BackgroundTask(@NonNull Task task) {
        this.task = task;
    }

    /**
     * @return the unique task identifier
     */
    protected abstract int getTaskId();

    /**
     * @param httpCode the http response code
     * @param result   the result from server an Object type of String (Server) or Exception (Local)
     */
    protected void executed(int httpCode, @Nullable Object result) {
        this.task.onTaskCompleted(this.getTaskId(), httpCode, result);
    }
}
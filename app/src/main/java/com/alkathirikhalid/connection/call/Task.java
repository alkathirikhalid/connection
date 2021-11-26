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

import androidx.annotation.Nullable;

/**
 * Call back mechanism for all tasks
 *
 * @author alkathirikhalid
 * @version 2.1.0
 */
public interface Task {
    /**
     * Notify observing class on task completed
     *
     * @param task     the task unique identifier
     * @param httpCode the http response code
     * @param result   the result object of String (Server) or Exception (Locally)
     */
    void onTaskCompleted(int task, int httpCode, @Nullable Object result);
}
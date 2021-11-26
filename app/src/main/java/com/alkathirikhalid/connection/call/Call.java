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

import static com.alkathirikhalid.connection.call.Util.isSetDoOutput;
import static com.alkathirikhalid.connection.call.Util.isSuccessResponse;
import static com.alkathirikhalid.connection.call.Util.setDefaultMethodAndProperties;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Call</p>
 * Make synchronous (none blocking) http call / connection with a single line
 *
 * @author alkathirikhalid
 * @version 2.1.0
 */
public abstract class Call {

    // Global extra request properties such as security / date / authentication e.t.c for all calls
    private static final List<GlobalProperty> globalProperties = new ArrayList<>(); // Empty
    // Global connection and read time out fot all calls
    private static int timeOut = 3000; // default half a minute

    /**
     * Add global properties to be used in all / next calls
     *
     * @param globalProperty the key / vale pair as expected by server
     * @return true on successful adding, false on fail
     */
    public static boolean addGlobalProperty(GlobalProperty globalProperty) {
        return Call.globalProperties.add(globalProperty);
    }

    /**
     * Remove global property not to be used in all / next calls
     *
     * @param globalProperty the key / vale pair as expected by server
     * @return true on successful removal, false on fail
     */
    public static boolean removeGlobalProperty(GlobalProperty globalProperty) {
        return Call.globalProperties.remove(globalProperty);
    }

    /**
     * Optional setup for connection and read time out / default 3000 (30 seconds)
     *
     * @param timeOut in milliseconds, 0 for indefinite (not advisable)
     */
    public static void setTimeOut(int timeOut) {
        Call.timeOut = timeOut;
    }

    /**
     * Executes synchronous (none blocking) http call
     *
     * @param taskId              the unique identifier of the task
     * @param task                the task call back after completion or failure
     * @param method              the http method POST, GET, PUT and DELETE
     * @param url                 the full url including parameters for GET
     * @param contentTypeRequest  the request type expected by server
     * @param contentTypeResponse the response type expected back
     * @param requestBody         the payload to be submit to the server
     */
    public static void execute(int taskId, Task task, Method method, String url, ContentType contentTypeRequest, ContentType contentTypeResponse, String requestBody) {
        BackgroundTask backgroundTask = new BackgroundTask(task) {
            @Override
            public int getTaskId() {
                return taskId;
            }

            @Override
            public void run() {
                try {
                    // Local declaration inside a thread life cycle / method
                    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                    // Set User preferred method, content request and content response type
                    connection = setDefaultMethodAndProperties(connection, method, contentTypeRequest, contentTypeResponse);

                    // Global connection and read time out fot all calls
                    connection.setConnectTimeout(timeOut);
                    connection.setReadTimeout(timeOut);

                    // Global extra request properties such as security / date / authentication / authorization e.t.c for all calls
                    for (GlobalProperty globalProperty : globalProperties) {
                        connection.setRequestProperty(globalProperty.getPropertyName(), globalProperty.getGetPropertyValue());
                    }

                    // check and set if required for this call
                    connection.setDoOutput(isSetDoOutput(method));
                    if (requestBody != null) // Enforce request body protocol support
                        connection.getOutputStream().write(requestBody.getBytes());

                    // connect to validate server response
                    connection.connect();
                    // receive response from server success / fail from server
                    InputStream inputStream = isSuccessResponse(connection.getResponseCode()) ? connection.getInputStream() : connection.getErrorStream();
                    // Prepare to read response from server
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String data;
                    StringBuilder response = new StringBuilder();
                    while ((data = bufferedReader.readLine()) != null) {
                        response.append(data);
                    }
                    // http response code and received data can be success / fail from server
                    executed(connection.getResponseCode(), response.toString());
                } catch (Exception exception) {
                    // Error occurred locally no response from server, http code 0 with the local exception
                    executed(0, exception);
                }
            }
        };
        // Set a name to the backgroundTask for unique identification
        // required for canceling task premature when required
        backgroundTask.setName(String.valueOf(taskId));
        // Start backgroundTask
        backgroundTask.start();
    }

    /**
     * To Cancel backgroundTask premature when needed
     *
     * @param taskId the task unique identifier
     * @return true if found and canceled else false if not
     */
    public static boolean cancelTask(int taskId) {
        for (Thread backgroundTask : Thread.getAllStackTraces().keySet()) {
            if (backgroundTask.getName().equals(String.valueOf(taskId))) {
                backgroundTask.interrupt();
                return true;
            }
        }
        return false;
    }
}

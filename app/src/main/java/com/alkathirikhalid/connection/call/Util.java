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

import static com.alkathirikhalid.connection.call.ContentType.ACCEPT;
import static com.alkathirikhalid.connection.call.ContentType.CONTENT_TYPE;

import java.net.HttpURLConnection;
import java.net.ProtocolException;

/**
 * A Call Util Class
 *
 * @author alkathirikhalid
 * @version 2.1.0
 */
public class Util {
    /**
     * Adds single or multiple properties to the call
     *
     * @param connection          the connection instance
     * @param method              the connection http method POST, GET, PUT, DELETE
     * @param contentTypeRequest  the request content type
     * @param contentTypeResponse the response content type
     * @return the finalized connection with the added properties
     * @throws ProtocolException protocol exception such as TCP error
     */
    protected static HttpURLConnection setDefaultMethodAndProperties(HttpURLConnection connection, Method method, ContentType contentTypeRequest, ContentType contentTypeResponse) throws ProtocolException {
        connection.setRequestMethod(method.name());
        connection.setRequestProperty(CONTENT_TYPE, contentTypeRequest.toString());
        connection.setRequestProperty(ACCEPT, contentTypeResponse.toString());
        return connection;
    }

    /**
     * @param method the method to be checked
     * @return true for POST and PUT only
     */
    protected static boolean isSetDoOutput(Method method) {
        return (method.equals(Method.POST) || method.equals(Method.PUT));
    }

    /**
     * Check response from server as being success
     *
     * @param responseCode the receive http response from server
     * @return true for 200 range response exclusive 200, 201 and 204
     */
    public static boolean isSuccessResponse(int responseCode) {
        return (responseCode == 200 || responseCode == 201 || responseCode == 204);
    }
}

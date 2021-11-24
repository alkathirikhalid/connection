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

public class Util {
    protected static HttpURLConnection setDefaultMethodAndProperties(HttpURLConnection connection, Method method, ContentType contentTypeRequest, ContentType contentTypeResponse) throws ProtocolException {
        connection.setRequestMethod(method.name());
        connection.setRequestProperty(CONTENT_TYPE, contentTypeRequest.toString());
        connection.setRequestProperty(ACCEPT, contentTypeResponse.toString());
        return connection;
    }

    protected static boolean isSetDoOutput(Method method) {
        return (method.equals(Method.POST) || method.equals(Method.PUT));
    }

    public static boolean isSuccessResponse(int responseCode) {
        return (responseCode == 200 || responseCode == 201 || responseCode == 204);
    }
}

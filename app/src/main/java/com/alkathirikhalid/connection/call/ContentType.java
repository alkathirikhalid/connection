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

/**
 * Supported Call Content Types
 *
 * @author alkathirikhalid
 * @version 2.1.0
 */
public enum ContentType {
    // Request / Response expected types
    APPLICATION_JSON("application/json"),
    MULTIPART_FORM_DATA("multipart/form-data"),
    APPLICATION_FORM_URLENCODED("application/x-www-form-urlencoded");

    // Keys for requests
    protected static final String CONTENT_TYPE = "Content-Type";
    protected static final String ACCEPT = "Accept";

    // Member value
    private final String value;

    // constructor to set the value
    ContentType(String value) {
        this.value = value;
    }

    @NonNull
    @Override
    public String toString() {
        return value;
    }
}

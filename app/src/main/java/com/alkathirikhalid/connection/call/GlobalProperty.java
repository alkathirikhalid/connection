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

/**
 * <p>Allow to add more properties to the call</p>
 * Supported Call Key / Value Property, user can add properties
 * such as security / date / authentication e.t.c
 *
 * @author alkathirikhalid
 * @version 2.1.0
 */
public class GlobalProperty {
    private final String propertyName;
    private final String propertyValue;

    /**
     * Constructor
     *
     * @param propertyName  the property name
     * @param propertyValue the property value
     */
    public GlobalProperty(String propertyName, String propertyValue) {
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }

    /**
     * @return the property name
     */
    protected String getPropertyName() {
        return propertyName;
    }

    /**
     * @return the property value
     */
    protected String getGetPropertyValue() {
        return propertyValue;
    }
}
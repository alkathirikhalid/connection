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
package com.alkathirikhalid.connection.network;

/**
 * <p>Connection Change Call Back.</p>
 * An <code>Interface</code> that provides a Call Back to notify
 * when Connection has Changed.
 *
 * @author alkathirikhalid
 * @version 2.1.0
 */

interface ConnectionChangeListener {

    /**
     * <p>Notify there is connectivity or is in the process of being established.</p>
     */
    void connectedOrConnecting();

    /**
     * <p>Notify there is connectivity and it is possible for an internet connection.</p>
     */
    void connected();

    /**
     * <p>Notify there is connectivity to WIFI.</p>
     */
    void typeWifi();

    /**
     * <p>Notify there is connectivity to MOBILE.</p>
     */
    void typeMobile();

    /**
     * <p>Notify there is connectivity to MOBILE data speed of about 400kbps and above.</p>
     */
    void connectedConnectionFast();

    /**
     * <p>Notify there is connectivity to MOBILE data speed of about 14kbps to 100kbps.</p>
     */
    void connectedConnectionSlow();

    /**
     * <p>Notify there is no connectivity.</p>
     */
    void noNetwork();
}

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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * <p>Connection Change Broadcast Receiver.</p>
 * A subclass of <code>BroadcastReceiver</code> that will receive intents sent by <code>sendBroadcast()</code>.
 *
 * @author alkathirikhalid
 * @version 1.05
 */

class ConnectionChange extends BroadcastReceiver {

    /**
     * <p>An Interface that provides a Call Back Mechanism to be notified
     * when Connection Change has taken place.</p>
     */
    private ConnectionChangeListener connectionChangeListener;

    /**
     * <p>Constructor adhering to Observer Pattern.</p>
     * To be used after <code>ConnectionChange()</code>.
     *
     * @param connectionChangeListener The Connection Change Call Back.
     * @return The Initialized linked Connection Change Call Back.
     */
    ConnectionChange setConnectionChangeListener(ConnectionChangeListener connectionChangeListener) {
        this.connectionChangeListener = connectionChangeListener;
        return this;
    }

    /**
     * This method is called when the BroadcastReceiver is receiving an Intent
     * broadcast.  During this time you can use the other methods on
     * BroadcastReceiver to view/modify the current result values.  This method
     * is always called within the main thread of its process, unless you
     * explicitly asked for it to be scheduled on a different thread using
     * {link Context#registerReceiver(BroadcastReceiver, * IntentFilter, String, Handler)}.
     * When it runs on the main thread you should
     * never perform long-running operations in it (there is a timeout of
     * 10 seconds that the system allows before considering the receiver to
     * be blocked and a candidate to be killed). You cannot launch a popup dialog
     * in your implementation of onReceive().
     * <p>
     * <p><b>If this BroadcastReceiver was launched through a &lt;receiver&gt; tag,
     * then the object is no longer alive after returning from this
     * function.</b>  This means you should not perform any operations that
     * return a result to you asynchronously -- in particular, for interacting
     * with services, you should use
     * {@link Context#startService(Intent)} instead of
     * {link Context#bindService(Intent, ServiceConnection, int)}.  If you wish
     * to interact with a service that is already running, you can use
     * {@link #peekService}.
     * <p>
     * <p>The Intent filters used in {@link Context#registerReceiver}
     * and in application manifests are <em>not</em> guaranteed to be exclusive. They
     * are hints to the operating system about how to find suitable recipients. It is
     * possible for senders to force delivery to specific recipients, bypassing filter
     * resolution.  For this reason, {@link #onReceive(Context, Intent) onReceive()}
     * implementations should respond only to known actions, ignoring any unexpected
     * Intents that they may receive.
     *
     * @param context The Context in which the receiver is running.
     * @param intent  The Intent being received.
     */
    @Override
    public void onReceive(Context context, Intent intent) {

        // Checks if there are linked listeners before invoking change
        if (connectionChangeListener != null) {
            // Checks if there is connectivity or is in the process of being established
            if (Connection.isConnectedOrConnecting(context)) {
                // Notifies there is connectivity or is in the process of being established
                connectionChangeListener.connectedOrConnecting();
            }
            // Checks if there is connectivity and it is possible to pass data
            if (Connection.isConnected(context)) {
                // Notifies there is connectivity and it is possible to pass data
                connectionChangeListener.connected();
            }
            // Checks if there is connectivity to WIFI
            if (Connection.isTypeWifi(context)) {
                // Notifies there is connectivity to WIFI
                connectionChangeListener.typeWifi();
            }
            // Checks if there is connectivity to MOBILE
            if (Connection.isTypeMobile(context)) {
                // Notifies there is connectivity to MOBILE
                connectionChangeListener.typeMobile();
            }
            // Checks if there is connectivity to MOBILE data speed of about 400kbps to 23Mbps
            if (Connection.isConnectedConnectionFast(context)) {
                // Notifies there is connectivity to MOBILE data speed of about 400kbps to 23Mbps
                connectionChangeListener.connectedConnectionFast();
            }
            // Checks if there is connectivity to MOBILE data speed of about 14kbps to 100kbps
            if (Connection.isConnectedConnectionSlow(context)) {
                // Notifies there is connectivity to MOBILE data speed of about 14kbps to 100kbps
                connectionChangeListener.connectedConnectionSlow();
            }
            // Checks there is no connectivity
            if (!Connection.isConnectedOrConnecting(context)) {
                // Notifies there is no connectivity
                connectionChangeListener.noNetwork();
            }
        }
    }
}
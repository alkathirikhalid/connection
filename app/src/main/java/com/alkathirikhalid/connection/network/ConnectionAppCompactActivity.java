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

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * <p>Enhanced Base class with Real Time Connection Changes.</p>
 * For activities that use the
 * <a href="{@docRoot}tools/extras/support-library.html">support library</a>
 * action bar features.
 *
 * @author alkathirikhalid
 * @version 1.05
 */

public abstract class ConnectionAppCompactActivity extends AppCompatActivity implements ConnectionChangeListener {

    /**
     * <p>Connection Change Broadcast Receiver.</p>
     */
    private ConnectionChange connectionChange;

    /**
     * <p>Structured description of Intent values to be matched.</p>
     */
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Creates new IntentFilter that matches a change in network connectivity
        intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);

        // Creates new Connection Change Broadcast Receiver
        connectionChange = new ConnectionChange();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Register for Connection Change that matches a change in network connectivity
        this.registerReceiver(connectionChange, intentFilter);

        // Registers the connection change call back
        connectionChange.setConnectionChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Unregister the Connection Change Broadcast Receiver
        unregisterReceiver(connectionChange);

        // Unregisters the connectivity change call back
        connectionChange.setConnectionChangeListener(null);
    }

    // Implemented methods

    @Override
    public void connectedOrConnecting() {
        // To be implemented if / when needed on host app
    }

    @Override
    public void connected() {
        // To be implemented if / when needed on host app
    }

    @Override
    public void typeWifi() {
        // To be implemented if / when needed on host app
    }

    @Override
    public void typeMobile() {
        // To be implemented if / when needed on host app
    }

    @Override
    public void connectedConnectionFast() {
        // To be implemented if / when needed on host app
    }

    @Override
    public void connectedConnectionSlow() {
        // To be implemented if / when needed on host app
    }

    @Override
    public void noNetwork() {
        // To be implemented if / when needed on host app
    }
}

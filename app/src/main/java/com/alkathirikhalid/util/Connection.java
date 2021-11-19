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
package com.alkathirikhalid.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * <p>Connection utility.</p>
 * <p>
 * Monitor network connections like Wi-Fi, GPRS, UMTS, etc for API 16 and above,
 * this class attempt to fail over to another network when connectivity to a network is lost
 * by checking <code>isConnectedOrConnecting</code>, it provides a way that allows applications to
 * query the coarse-grained or fine-grained state of the available networks by checking
 * <code>isTypeMobile</code> or <code>isTypeWifi</code>. It also provides a way that allows
 * applications to request and check networks for their fast data traffic by checking
 * <code>isConnectedConnectionFast</code> or <code>isConnectedConnectionSlow</code>.</p>
 * <p>
 * <strong>It is safer to check <code>isConnectedConnectionSlow</code> first or only rather than
 * <code>isConnectedConnectionFast</code> first or combined, to allow forward compatibility in the
 * event faster mobile connection are introduce in the future that are above 20 plus Mbps.<strong/><p/>
 *
 * @author alkathirikhalid
 * @version 1.05
 */
public abstract class Connection {

    /**
     * <p>Serves the <code>Connection</code> public methods with a way to get an instance of the
     * current network Connection.</p>
     *
     * @param context of an Application, Activity, Service or IntentService.
     * @return an instance that represents the current network connection.
     */
    private static NetworkInfo getActiveNetworkInfo(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService
                (Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo();
    }

    /**
     * <p>Checks if there is connectivity or is in the process of being established.
     * This is applicable for applications that need to do any less intensive or slow
     * network related activities.</p>
     *
     * @param context of an Application, Activity, Service or IntentService.
     * @return <code>true</code> if there is connectivity or is in the process of being established,
     * <code>false</code> otherwise.
     */
    public static boolean isConnectedOrConnecting(Context context) {
        NetworkInfo networkInfo = Connection.getActiveNetworkInfo(context);
        return (networkInfo != null && networkInfo.isConnectedOrConnecting());
    }

    /**
     * <p>Checks if there is connectivity and it is possible to pass data.
     * This is applicable for applications that need to do any intensive network related activities
     * like data transactions such as read or write data, <code>isConnected</code> guarantees that
     * the network is fully usable unlike <code>isConnectedOrConnecting</code> which dose not.</p>
     *
     * @param context of an Application, Activity, Service or IntentService.
     * @return <code>true</code> if connectivity exists, <code>false</code> otherwise.
     */
    public static boolean isConnected(Context context) {
        NetworkInfo networkInfo = Connection.getActiveNetworkInfo(context);
        return (networkInfo != null && networkInfo.isConnected());
    }

    /**
     * <p>Checks if there is connectivity to WIFI, when active, all data traffic will use this network.
     * <code>isTypeWifi</code> automatically calls <code>isConnected()</code> to guarantees that
     * connectivity exists.</p>
     *
     * @param context of an Application, Activity, Service or IntentService.
     * @return <code>true</code> if WIFI, <code>false</code> otherwise.
     */
    public static boolean isTypeWifi(Context context) {
        NetworkInfo networkInfo = Connection.getActiveNetworkInfo(context);
        return (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() ==
                ConnectivityManager.TYPE_WIFI);
    }

    /**
     * <p>Checks if there is connectivity to MOBILE data, when active, all data traffic will use
     * this network. <code>isTypeMobile</code> automatically calls <code>isConnected()</code>
     * to guarantees that connectivity exists.</p>
     *
     * @param context of an Application, Activity, Service or IntentService.
     * @return <code>true</code> if MOBILE data, <code>false</code> otherwise.
     */
    public static boolean isTypeMobile(Context context) {
        NetworkInfo networkInfo = Connection.getActiveNetworkInfo(context);
        return (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() ==
                ConnectivityManager.TYPE_MOBILE);
    }

    /**
     * <p>Checks if there is connectivity to MOBILE data speed of about 400kbps to 23Mbps.
     * <code>isConnectedConnectionFast</code> automatically calls <code>isConnected()</code>
     * to guarantees that connectivity exists.</p>
     *
     * @param context of an Application, Activity, Service or IntentService.
     * @return <code>true</code> if MOBILE data, sub type EVDO0, EVDOA, EVDOB, UMTS, EHRPD, HSDPA,
     * HSPA, HSUPA, HSPAP or LTE <code>false</code> otherwise.
     */
    public static boolean isConnectedConnectionFast(Context context) {
        NetworkInfo networkInfo = Connection.getActiveNetworkInfo(context);
        return (networkInfo != null && networkInfo.isConnected() && Connection.isConnectionFast
                (networkInfo.getType(), networkInfo.getSubtype()));
    }

    /**
     * <p>Checks if there is connectivity to MOBILE data speed of about 14kbps to 100kbps.
     * <code>isConnectedConnectionSlow</code> automatically calls <code>isConnected()</code>
     * to guarantees that connectivity exists.</p>
     *
     * @param context of an Application, Activity, Service or IntentService.
     * @return <code>true</code> if MOBILE data, sub type CDMA, 1xRTT, EDGE, GPRS or IDEN
     * <code>false</code> otherwise.
     */
    public static boolean isConnectedConnectionSlow(Context context) {
        NetworkInfo networkInfo = Connection.getActiveNetworkInfo(context);
        return (networkInfo != null && networkInfo.isConnected() && Connection.isConnectionSlow
                (networkInfo.getType(), networkInfo.getSubtype()));
    }

    /**
     * <p>Serves <code>isConnectedConnectionFast</code> public methods with a way to detect the
     * current sub type connection of MOBILE type.</p>
     *
     * @param type    of connection either WIFI or MOBILE.
     * @param subType of a MOBILE connection type.
     * @return <code>true</code> if WIFI or if MOBILE data speed of about 400kbps to 23Mbps
     * <code>false</code> otherwise.
     */
    private static boolean isConnectionFast(int type, int subType) {
        if (type == ConnectivityManager.TYPE_WIFI) {
            return true;
        } else if (type == ConnectivityManager.TYPE_MOBILE) {
            switch (subType) {
                // Speeds of about ~ 400 to 1000 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    // Speeds of about ~ 600 to 1400 kbps.
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    // Speeds of about ~ 2 to 14 Mbps.
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                    // Speeds of about ~ 700 to 1700 kbps
                case TelephonyManager.NETWORK_TYPE_HSPA:
                    // Speeds of about ~ 1 to 23 Mbps
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                    // Speeds of about ~ 400 to 7000 kbps
                case TelephonyManager.NETWORK_TYPE_UMTS:
                    // Speeds of about ~ 1 to 2 Mbps
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                    // Speeds of about ~ 5 Mbps
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    // Speeds of about ~ 10 to 20 Mbps
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                    // Speeds of about ~ 10+ Mbps
                case TelephonyManager.NETWORK_TYPE_LTE:
                    return true;
                // Otherwise
                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                default:
                    return false;
            }
        } else {
            return false;
        }
    }

    /**
     * <p>Serves <code>isConnectedConnectionSlow</code> public methods with a way to detect the
     * current sub type connection of MOBILE type.</p>
     *
     * @param type    of connection either WIFI or MOBILE.
     * @param subType of a MOBILE connection type.
     * @return <code>true</code> if MOBILE data speed of about 14kbps to 100kbps
     * <code>false</code> otherwise.
     */
    private static boolean isConnectionSlow(int type, int subType) {
        if (type == ConnectivityManager.TYPE_WIFI) {
            return false;
        } else if (type == ConnectivityManager.TYPE_MOBILE) {
            switch (subType) {
                // Speeds of about ~ 50 to 100 kbps
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                    // Speeds of about ~ 14 to 64 kbps
                case TelephonyManager.NETWORK_TYPE_CDMA:
                    // Speeds of about ~ 50 to 100 kbps
                case TelephonyManager.NETWORK_TYPE_EDGE:
                    // Speeds of about ~ 100 kbps
                case TelephonyManager.NETWORK_TYPE_GPRS:
                    // Speeds of about ~ 25 kbps
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    return true;
                // Otherwise
                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                default:
                    return false;
            }
        } else {
            return false;
        }
    }
}
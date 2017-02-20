[![](https://jitpack.io/v/alkathirikhalid/connection.svg)](https://jitpack.io/#alkathirikhalid/connection)

# Connection
An Android utility class to Monitor network connections like Wi-Fi, GPRS, UMTS, etc for API 16 and above, this class attempt to fail over to another network when connectivity to a network is lost by checking <code>isConnectedOrConnecting</code>, it provides a way that allows applications to query the coarse-grained or fine-grained state of the available networks by checking <code>isTypeMobile</code> or <code>isTypeWifi</code>. It also provides a way that allows applications to request and check networks for their fast data traffic by checking <code>isConnectedConnectionFast</code> or <code>isConnectedConnectionSlow</code>. It can be used in any Android project either using build script or adding aar file.

# Usage
<p><strong>It is safer to check <code>isConnectedConnectionSlow</code> first or only rather than <code>isConnectedConnectionFast</code> first or combined, to allow forward compatibility in the event faster mobile connection are introduce in the future that are above 20 plus Mbps.</strong></p>

<p>Add permission in Android Manifest File <pre>&lt;uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/&gt;</pre></p>

<p>All accessible and usable methods automatically calls <code>isConnected()</code> to guarantees that connectivity exists except for <code>isConnectedOrConnecting</code> depending on your application needs.</p>
<code>Connection.isConnectedConnectionSlow(context);</code>
### Accesible and usable Methods
<p><code>isConnectedOrConnecting()</code> Checks if there is connectivity or is in the process of being established. This is applicable for applications that need to do any less intensive or slow network related activities.</p>
<p><code>isConnected()</code> Checks if there is connectivity and it is possible to pass data. This is applicable for applications that need to do any intensive network related activities like data transactions such as read or write data, guarantees that the network is fully usable.</p>
<p><code>isTypeWifi()</code> Checks if there is connectivity to WIFI, when active, all data traffic will use this network.</p>
<p><code>isTypeMobile()</code> Checks if there is connectivity to MOBILE data, when active, all data traffic will use this network.</p>
<p><code>isConnectedConnectionFast()</code> Checks if there is connectivity to MOBILE data speed of about 400kbps to 23Mbps. Supports subtype EVDO0, EVDOA, EVDOB, UMTS, EHRPD, HSDPA, HSPA, HSUPA, HSPAP and LTE.</p>
<p><code>isConnectedConnectionSlow()</code>Checks if there is connectivity to MOBILE data speed of about 14kbps to 100kbps. Supports subtype CDMA, 1xRTT, EDGE, GPRS and IDEN.</p>

# Installation
### Gradle
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
```
dependencies {
	        compile 'com.github.alkathirikhalid:connection:v1.01'
	}
  ```
### Maven
  ```
  <repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
  ```
  ```
  <dependency>
	    <groupId>com.github.alkathirikhalid</groupId>
	    <artifactId>connection</artifactId>
	    <version>v1.01</version>
	</dependency>
  ```
  
# Further Resources
<ul>
<li>Document download: https://github.com/alkathirikhalid/connection/releases/download/v1.01/docs.zip</li>
<li>AAR download: https://github.com/alkathirikhalid/connection/releases/download/v1.01/connection.aar</li>
</ul>
  
# License

Copyright 2015 Al-Kathiri Khalid www.alkathirikhalid.com

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

[![](https://jitpack.io/v/alkathirikhalid/connection.svg)](https://jitpack.io/#alkathirikhalid/connection)

# Eloquent Connection
<p>Android light weight library made up of two features <b>Call</b> and <b>Network</b>. Connection can be used in any Android project either using build script or adding aar file, no need to declare any thing else as all the permissions will be merged, it is simply a plug and play library!</p>

<p><b>- Network</b> : Monitors connections like Wi-Fi, GPRS, UMTS, e.t.c. Device network usually attempts to fail over to another network connection when connectivity to a given network is lost, Network feature simply allows checking such as <code>isConnectedOrConnecting</code> to get the device network state. It also provides a way that allows apps to query the coarse-grained or fine-grained state of the available networks by checking <code>isTypeMobile</code> or <code>isTypeWifi</code>. It also allows apps to request and check networks for their fast data traffic by checking <code>isConnectedConnectionFast</code> or <code>isConnectedConnectionSlow</code>.<p>

<p><b>- Call</b> : Makes asynchronous / non blocking http request with easy in a single line. To make a call simply do <b>2 things out of the box </b><code>Call.execute(taskId,task,method,url,contentTypeRequest,contentTypeResponse,requestBody)</code>, and make your Class / Activity / Fragment / Model / Repositry whatever architecture you have <code>implements Task</code> where <code>onTaskCompleted(int task, int httpCode, Object result)</code> returns the response, thats it, 2 steps. It is also possible to make synchronous / blocking http request by simply cascaded the calls on Task completed for the consecutive calls.</p>

# Call Usage
<p><code>implements Task</code> in a class that is expected to receive http responses on task completed such as an Activity / Fragment / Model / Repositry whatever architecture you have, pass a unique task ID in the range of 1000 or as you so choose and pass the instance of the class that implemented the Task interface example call/s in one class for simple demo</p>

```
public class MainActivity extends AppCompatActivity implements Task {
...

        // Global settings affecting all calls just to be set once or changed before a specific / special call
        Call.setTimeOut(5000); // not required only when needed, default half a minute / 30 seconds / 3000 milliseconds
	
	// Global property add if needed, list support removed to make the library simple with 5 methods for any dev level
	// Global property such as security / date / authentication e.t.c for all calls, just set once if needed
        GlobalProperty globalProperty1 = new GlobalProperty("Authorization", "Bearer 3672e8ae491a13072c1-YOUR-KEY-48afb6c6d3");
        // Adding global property
        Call.addGlobalProperty(globalProperty1); // can be repeated for globalProperty2 e.t.c
        // Removing global property
        // Call.removeGlobalProperty(globalProperty1); // can be added / removed vice verser for specific flow
	
	
	// POST Sample
	String postData = "{\"name\":\"Khalid\",\"email\":\"khalid@alkathirikhalid.com\",\"gender\":\"male\",\"status\":\"active\"}";
	String postURL = "https://gorest.co.in/public/v1/users";

        // GET Sample
        String getData = null;
        String getURL = "https://gorest.co.in/public/v1/users/2676";
	
	// PUT Sample
        String putData = "{\"name\":\"Al Kathiri Khalid\",\"email\":\"k@alkathirikhalid.com\",\"gender\":\"male\",\"status\":\"active\"}";
        String putURL = "https://gorest.co.in/public/v1/users/2676";
	
	// DELETE Sample
        String deleteData = null;
        String deleteURL = "https://gorest.co.in/public/v1/users/2676";
	
	// Execute CRUD Call
        Call.execute(1000, this, POST, postURL, APPLICATION_JSON, APPLICATION_JSON, postData);
        //Call.execute(2000, this, GET, getURL, APPLICATION_JSON, APPLICATION_JSON, null);
        //Call.execute(3000, this, PUT, putURL, APPLICATION_JSON, APPLICATION_JSON, putData);
        //Call.execute(4000, this, DELETE, deleteURL, APPLICATION_JSON, APPLICATION_JSON, null);
	
	// Cancel executed Call if not needed for any reason example as leaving screen such as onPause e.t.c
        // Call.cancelTask(4000);
...

    @Override
    public void onTaskCompleted(int task, int httpCode, @Nullable Object result) {
        Log.d("***** Task ID: ", String.valueOf(task)); // the task unique identiifier provided in the execution of the call
        Log.d("***** HTTP CODE: ", String.valueOf(httpCode)); // server response range 2xx success, range 4xx - 5xx error (normal http response) or 0 local error
        Log.d("***** Data Received: ", String.valueOf(result)); // Object instance of String success / failure from Server or instance of Exception local error
        Log.d("***** is Success: ", String.valueOf(isSuccessResponse(httpCode))); // Call util to check success range / not needed
        if (result instanceof Exception) {
            // all local errors including Call cancled, the error string is provided, this will allow to stop loading screen / animation
            // all local errors will have http code 0
        }
...
    }		
```

### Call Accesible and usable Methods
<p>Call has a total of 5 methods where only 1 is need to make the call</p>
<p>- <code>Call.setTimeOut(5000)</code> Not required, time in milliseconds, default 3000 / 30 seconds.</p>
<p>- <code>Call.addGlobalProperty(globalProperty)</code> Not required, extra properties such as security / date / authentication / authorization.</p>
<p>- <code>Call.removeGlobalProperty(globalProperty)</code> Not required, only to remove extra propert if needed.</p>
<p>- <code>Call.execute(taskId,task,method,url,contentTypeRequest,contentTypeResponse,requestBody)</code> Required to make the call.</p>
<p>- <code>Call.cancelTask(taskId)</code> not required, only if needed for any reason such as leaving screen.</p>

<p>Supported metheds are <code>POST</code>, <code>GET</code>, <code>PUT</code> and <code>DELETE</code>.</p>
<p>Supported content types are <code>application/json</code>, <code>multipart/form-data</code> and <code>application/x-www-form-urlencoded</code></p>

# Network Usage
<p><strong>It is safer to check <code>isConnectedConnectionSlow</code> first or only rather than <code>isConnectedConnectionFast</code> first or combined, to allow forward compatibility in the event faster mobile connection are introduce in the future that are above 20 plus Mbps.</strong></p>

<p>All accessible and usable methods automatically calls <code>isConnected()</code> to guarantees that connectivity exists except for <code>isConnectedOrConnecting</code> depending on your application needs.</p>
<code>Connection.isConnectedConnectionSlow(context);</code>

### Network Accesible and usable Methods
<p>Network has a total of 6 methods.</p>
<p>- <code>isConnectedOrConnecting()</code> Checks if there is connectivity or is in the process of being established. This is applicable for applications that need to do any less intensive or slow network related activities.</p>
<p>- <code>isConnected()</code> Checks if there is connectivity and it is possible to pass data. This is applicable for applications that need to do any intensive network related activities like data transactions such as read or write data, guarantees that the network is fully usable.</p>
<p>- <code>isTypeWifi()</code> Checks if there is connectivity to WIFI, when active, all data traffic will use this network.</p>
<p>- <code>isTypeMobile()</code> Checks if there is connectivity to MOBILE data, when active, all data traffic will use this network.</p>
<p>- <code>isConnectedConnectionFast()</code> Checks if there is connectivity to MOBILE data speed of about 400kbps to 23Mbps. Supports subtype EVDO0, EVDOA, EVDOB, UMTS, EHRPD, HSDPA, HSPA, HSUPA, HSPAP and LTE.</p>
<p>- <code>isConnectedConnectionSlow()</code>Checks if there is connectivity to MOBILE data speed of about 14kbps to 100kbps. Supports subtype CDMA, 1xRTT, EDGE, GPRS and IDEN.</p>

### Network Real time updates
<p>To have connectivity updates in real time simply either replace:-</p>
<p>- <code>ConnectionActivity</code> in place of <code>Activity</code>.</p>
<p>- <code>ConnectionAppCompactActivity</code> in place of <code>AppCompactActivity</code>.</p>
<p>And Override any of the provided 7 methods, which gives real time updates of all 6 mothods explained in <b>Network Accesible and usable Methods</b> including the 7th being <code>noNetwork()</code>, or simply use them without real time update as seen fit such as calling <code>Connection.isConnectedConnectionSlow(context)</code>.</p>

```
public class MainActivity extends ConnectionAppCompactActivity {

// Optional realtime update, simply Override any or all / if needed

    }
    @Override
    public void connectedOrConnecting() {
        // TODO
    }

    @Override
    public void connected() {
        // TODO
    }

    @Override
    public void typeWifi() {
        // TODO
    }

    @Override
    public void typeMobile() {
        // TODO
    }

    @Override
    public void connectedConnectionFast() {
        // TODO
    }

    @Override
    public void connectedConnectionSlow() {
        // TODO
    }

    @Override
    public void noNetwork() {
        // TODO
    }
}
```

### Eloquent Connection Permission and Requirements
<p>The minimun Android API 16. Thats it!</p>
<p><strong>The library will handle the system broadcast and permissions automatically, simply add the library via aar file or as a dependency declaration and use it out of the box without further settings, Eloquent Connection has already declared for internet and network state.</strong></p>

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
	        implementation 'com.github.alkathirikhalid:connection:v2.0.0'
	}
  ```
  ### Gradle 7 issues
A problem occurred will occur where the declared jitpack repo in project will fail to resolve / get the connection library

Simple Fix, in <b>settings.gradle</b> add jitpack repo, it must be present in settings.gradle on faliure will trigger dependencyResolutionManagement
```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' } // Jitpack Repo
        jcenter() // Warning: this repository is going to shut down soon
    }
}
rootProject.name = "Your App"
include ':app'

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
	    <version>v2.1.0</version>
	</dependency>
  ```
  
# Further Resources
<ul>
<li>Document download: https://github.com/alkathirikhalid/connection/releases/download/v2.1.0/call-docs.zip</li>
<li>Document download: https://github.com/alkathirikhalid/connection/releases/download/v2.1.0/network-docs.zip</li>
<li>AAR download: https://github.com/alkathirikhalid/connection/releases/download/v2.1.0/connection.aar</li>
<li>JAR download: https://github.com/alkathirikhalid/connection/releases/download/v2.1.0/call.jar</li>
</ul>
  
# License

Copyright 2015 - 2021 Al-Kathiri Khalid www.alkathirikhalid.com

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

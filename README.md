# RetrofitCache
A sample Android app which showcases usage of Cachable Retrofit -A type-safe HTTP client-
Service response is caching in Android app with an existing SQLite database and reading from cache when callback calls onFailure() method with RetrofitError.Kind.NETWORK. api.openweathermap is used in project to show hourly weather condition of izmir / Turkey with caching mechanism. Using the switch button at the top of the screen, you can change the Internet connectivity status. It pretends like there is no Internet connection. After switching from Online Mode to Offline Mode refresh the page with refresh button. Now you are reading data from cache.
        
In ideal scenario (in real implementation) you dont need to use switch button. When you disable your device internet connection (of course refresh again) you will see the cached data. In real implementation also you dont need to offlineMode control in onSuccess method of RetrofitCallback class; comment out this control.
        
Caching starts (Write to SQLite database) -> When callback calls success() method and if CacheManager is active
Reading from cache starts (Reading from SQLite database) -> When callback calls onFailure() method with RetrofitError.Kind.NETWORK and if CacheManager is active

# Libraries
- Retrofit - http://square.github.io/retrofit
- Picasso - http://square.github.io/picasso
- OkHttp - http://square.github.io/okhttp

# To Do
 In real implementation you dont need to offlineMode control in onSuccess method of RetrofitCallback class.
 ```
 // Comment out this control
 if (MainActivity.isOfflineMode) {
    failure(RetrofitError.networkError("customNetworkError", new IOException()));
    return;
 }
  ```
 

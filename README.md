# sunbot-http-client
Client that send messages to [Sunbot HTTP Server](https://github.com/Harium/sunbot-http) (compatible with Android and Java)

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.harium.suneidesis/sunbot-http/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.harium.suneidesis/sunbot-http/)

## How to use it

```java
    SunbotHttpClient client = new SunbotHttpClient();
    client.sendMessage("http://localhost:11883", "Hello World!");
```

This library uses [OkHttp](https://square.github.io/okhttp/) to send post messages.
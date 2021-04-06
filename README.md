# How To Test

1. Execute  ```mvn clean package -P <profile>``` 

    |   lab # |        profile | description |
    |:-------:|:--------------|:---|
    |   1     | lab1-standalone| Standalone implementation |
    |   1     | lab1-j2ee      | J2EE Tomee based implementation |
    |   2     | lab2-standalone|  |

2. Execute ```run``` script which deploys docker containers with Web Service and Database
3. Execute ```java -jar wslab-client.jar```

# Example
1. ```mvn clean package -P lab2-standalone```
2. ```run.bat```
3. ```java -jar wslab-client.jar get```
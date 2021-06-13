# How To Test

1. Execute  ```mvn clean package -P <profile>``` 

    |   lab # |        profile | description |
    |:-------:|:--------------|:---|
    |   1     | lab-1-standalone| Standalone implementation |
    |   1     | lab-1-j2ee      | J2EE Tomee based implementation |
    |   2     | lab-2|  |
    |   3     | lab-3|  |
    |   4     | lab-4|   |
    |   5     | lab-5|   |
    |   6     | lab-6|   |
    |   7     | lab-7|

2. Execute ```run``` script which deploys docker containers with Web Service and Database
3. Execute ```java -jar wslab-client.jar```

# Example
1. ```mvn clean package -P lab-2-standalone```
2. ```run.bat```
3. ```java -jar wslab-client.jar get```

# Lab 7 test
```ssh
mvn clean package -P lab-7
java -jar wslab-client.jar create -b 'business-name' -s 'service-name'
java -jar wslab-client.jar get -s 'service-name'
```
## Output
```ssh
service key: uddi:business-name:service-name
service name: 'service-name' 
```
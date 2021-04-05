# Lab 1
## Build
### Prerequisites
```ssh
cd base
mvn clean install
```
### Build Client
```ssh
cd client
mvn clean install
```
### Build Standalone
```ssh
cd lab1-standalone
mvn clean install
```
### Build J2EE
```ssh
cd lab1-j2ee
mvn clean install
```
## How to run webservice (need to be built before)
### Standalone
```ssh
cd containers
docker-compose up -d wslab-database
cd ../la1-standalone
java -jar wslab-server-standalone.jar
```
### J2EE
```ssh
cd containers
docker-compose up -d --force-recreate --build
```
### Access WebService using client (needs either standalone or J2EE webservice running)
```ssh
cd client
```
#### Client usage
```ssh
usage: wslab-client
 -d,--description <arg>    filter by DESCRIPTION
 -h,--help                 show help
 -i,--id <arg>             filter by ID
 -m,--manufacturer <arg>   filter by MANUFACTURER
 -n,--name <arg>           filter by NAME
 -p,--price <arg>          filter by PRICE
```
#### Example
```ssh
java -jar wslab-client --name "IPhone X"
```
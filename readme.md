# Run the demo server

## preconditions

 * docker is installed
 * docker compose is installed
 * maven is installed
 
## get it runnting

 * run ```mvn clean install``` on the base directory
 * run docker-compose up on the base directory
 * install a mobile test app like [GCM Server Helper]
 * register your device:
```
curl -X PUT http://docker:8080/gcm-server-test/rest/notification-topic/foobar/device-regs/GCM_API_KEY
```
 * send a message to your device:
```
curl -X POST http://docker:8080/gcm-server-test/rest/notification-topic/foobar -H  "Content-Type:application/json" --data "{\"notification\":{\"title\":\"hello push\"}, \"data\":{\"myprop\":\"test\"}}" -i
```

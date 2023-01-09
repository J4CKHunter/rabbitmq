
### rabbitmq queue example
#### you can find the examples of : 
<pre>
- headers exchange 
- topic exchange
- fanout exchange
- direct exchange
</pre>

#### activate the desired profile based on your exchange type preference on the application.properties file
<pre>
- spring.profiles.active=direct
- spring.profiles.active=fanout
- spring.profiles.active=headers
- spring.profiles.active=topic
</pre>
#### run rabbitmq instance with default id&password on docker:

```console
- docker run -d --hostname rabbitmq --name rabbitmq-container -p 5672:5672 -p 15672:15672 
-e RABBITMQ_DEFAULT_USER=test -e RABBITMQ_DEFAULT_PASS=test rabbitmq:3.10-management
```
#### you can reach the RabbitMQ Management UI in browser with the endpoint and the credentials
<pre>
http://localhost:15672/

Username: test
Password: test
</pre>
#### endpoints to publish message to queueA and queueB:

#### direct exchange

###### endpoint and message body
<pre>
    - http://localhost:8080/api/v1/customMessage?queue=queue-a

    {
        "message": "test message to queue a"
    }
</pre>

<pre>
    - http://localhost:8080/api/v1/customMessage?queue=queue-b

    {
        "message": "test message to queue b"
    }
</pre>

#### fanout exchange

###### endpoint and message body
<pre>
    - http://localhost:8080/api/v1/customMessage

    {
        "message": "test message to all queues"
    }
</pre>

#### headers exchange

###### endpoint and message body

<pre>
    - http://localhost:8080/api/v1/customMessage?type=urgent

    {
        "message": "test message urgent to queueA"
    }
</pre>

<pre>
    - http://localhost:8080/api/v1/customMessage?type=important

    {
        "message": "test message important to queueB"
    }
</pre>


#### topic exchange

###### endpoint and message body

<pre>
    - http://localhost:8080/api/v1/customMessage/queueA

    {
        "message": "test message to queue a"
    }
</pre>

<pre>
    - http://localhost:8080/api/v1/customMessage/queueB

    {
        "message": "test message to queue b"
    }
</pre>

##### you can use the example dockerfile to push the application to dockerhub

##### you can use the example docker-compose file to compose rabbitmq and the application
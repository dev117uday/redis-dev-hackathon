# URL Shortener : Microservice Maven


A URL shortening service with objective to:

- To provide a alias for long URLs
- Record the visits on the URL for info about its use

#### Typical Architecture
![12](https://user-images.githubusercontent.com/49728410/187259599-5440c310-d5df-41bd-8c41-883458d0351b.png)
#### Simplified Redis Stack Architecture
![18](https://user-images.githubusercontent.com/49728410/187259621-cf67128f-387e-4b27-8838-abfd6c3c86bf.png)


# Overview video (Optional)

Here's a short video that explains the project and how it uses Redis:

[![Embed your YouTube video](https://user-images.githubusercontent.com/49728410/187259926-bac0aee2-11c5-4af9-86dd-8a00cb2b2693.png)](https://www.youtube.com/watch?v=bB_ph71Oh7w)

## How it works

### How the data is stored:

For every URL info, there are 3 things store

- JSON containing all info
- Key Value pair `[short url]->[long url]`
- Redis Timeseries in name format  `links:{short_url}`

### How the data is accessed:

- When you hit the Go Service, it fetches the Long URL using the short one from key value store and sends a message to redsub with current timestamp, and redirect the user to Long URL
- Java subscribes to the channel : `pubsub:dev117uday` and when it receives the message, it stores into the Redis Timeseries
- When user can save new URL from frontend, it adds it to Redis JSON, it adds key-value and creates a new time-series for that link
- Frontend queries Redis JSON to get All links and the get info about particular link
- Frontend also queries the aggregator to fetch the value from redis time-series and show it in graph

## How to run it locally?

- To run go-service, inside the go directory run :
```bash
go run main.go
```

- To run the java-service, inside the java directory run :
```bash
mvn spring-boot:run
```

- To run the frontend,  inside the frontend directory run :
```bash
npm install
npm run dev
```

- Your redis stack database should be running on localhost:6379
- Make sure all the 4 things are running 
- Go the frontend app, save a new URL
- CLick more Info for the short URL
- go to : localhost:3000/{your short url}
- refresh the info page of frontend to see the timestamp

### Prerequisites

- JDK 17
- Go 1.18.5
- Node.js LTS

### Local installation

- To run go-service, inside the go directory run :
```bash
go run main.go
```

- To run the java-service, inside the java directory run :
```bash
mvn spring-boot:run
```

- To run the frontend,  inside the frontend directory run :
```bash
npm install
npm run dev
```


## More Information about Redis Stack

Here some resources to help you quickly get started using Redis Stack. If you still have questions, feel free to ask them in the [Redis Discord](https://discord.gg/redis) or on [Twitter](https://twitter.com/redisinc).

### Getting Started

1. Sign up for a [free Redis Cloud account using this link](https://redis.info/try-free-dev-to) and use the [Redis Stack database in the cloud](https://developer.redis.com/create/rediscloud).
1. Based on the language/framework you want to use, you will find the following client libraries:
   - [Redis OM .NET (C#)](https://github.com/redis/redis-om-dotnet)
     - Watch this [getting started video](https://www.youtube.com/watch?v=ZHPXKrJCYNA)
     - Follow this [getting started guide](https://redis.io/docs/stack/get-started/tutorials/stack-dotnet/)
   - [Redis OM Node (JS)](https://github.com/redis/redis-om-node)
     - Watch this [getting started video](https://www.youtube.com/watch?v=KUfufrwpBkM)
     - Follow this [getting started guide](https://redis.io/docs/stack/get-started/tutorials/stack-node/)
   - [Redis OM Python](https://github.com/redis/redis-om-python)
     - Watch this [getting started video](https://www.youtube.com/watch?v=PPT1FElAS84)
     - Follow this [getting started guide](https://redis.io/docs/stack/get-started/tutorials/stack-python/)
   - [Redis OM Spring (Java)](https://github.com/redis/redis-om-spring)
     - Watch this [getting started video](https://www.youtube.com/watch?v=YhQX8pHy3hk)
     - Follow this [getting started guide](https://redis.io/docs/stack/get-started/tutorials/stack-spring/)

The above videos and guides should be enough to get you started in your desired language/framework. From there you can expand and develop your app. Use the resources below to help guide you further:

1. [Developer Hub](https://redis.info/devhub) - The main developer page for Redis, where you can find information on building using Redis with sample projects, guides, and tutorials.
1. [Redis Stack getting started page](https://redis.io/docs/stack/) - Lists all the Redis Stack features. From there you can find relevant docs and tutorials for all the capabilities of Redis Stack.
1. [Redis Rediscover](https://redis.com/rediscover/) - Provides use-cases for Redis as well as real-world examples and educational material
1. [RedisInsight - Desktop GUI tool](https://redis.info/redisinsight) - Use this to connect to Redis to visually see the data. It also has a CLI inside it that lets you send Redis CLI commands. It also has a profiler so you can see commands that are run on your Redis instance in real-time
1. Youtube Videos
   - [Official Redis Youtube channel](https://redis.info/youtube)
   - [Redis Stack videos](https://www.youtube.com/watch?v=LaiQFZ5bXaM&list=PL83Wfqi-zYZFIQyTMUU6X7rPW2kVV-Ppb) - Help you get started modeling data, using Redis OM, and exploring Redis Stack
   - [Redis Stack Real-Time Stock App](https://www.youtube.com/watch?v=mUNFvyrsl8Q) from Ahmad Bazzi
   - [Build a Fullstack Next.js app](https://www.youtube.com/watch?v=DOIWQddRD5M) with Fireship.io
   - [Microservices with Redis Course](https://www.youtube.com/watch?v=Cy9fAvsXGZA) by Scalable Scripts on freeCodeCamp

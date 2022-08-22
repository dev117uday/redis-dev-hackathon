package main

import (
	"context"
	"log"
	"os"

	"github.com/go-redis/redis/v8"
	"github.com/gofiber/fiber/v2"
)

var ctx = context.Background()
var redisChannel = os.Getenv("RDPUBCH")
var hostUrl = os.Getenv("HOSTURL")

func main() {

	rdb := redis.NewClient(&redis.Options{
		Addr: os.Getenv("RDBURL"),
	})

	app := fiber.New()

	app.Get("/:shortenedUrl", func(c *fiber.Ctx) error {
		shortenedUrl := c.Params("shortenedUrl")

		longUrl, err := rdb.Get(ctx, shortenedUrl).Result()
		if err != nil {
			return c.SendString("Url not found ....")
		}

		var errorFlag bool

		err = rdb.Publish(ctx, redisChannel, shortenedUrl).Err()
		if err != nil {
			log.Fatal("unable to publish to redis pubsub")
			errorFlag = true
		}
		if errorFlag {
			return c.SendStatus(500)
		}

		https := longUrl[:8]
		http := longUrl[:7]

		if https != "https://" && http != "http://" {
			longUrl = "http://" + longUrl
		}

		return c.Redirect(longUrl)
	})

	app.Get("/", func(c *fiber.Ctx) error {
		return c.SendString("Looking for shortenedUrl ....")
	})

	app.Listen(hostUrl)

}

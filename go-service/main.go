package main

import (
	"context"
	"encoding/json"
	"log"
	"os"
	"time"

	"github.com/go-redis/redis/v8"
	"github.com/gofiber/fiber/v2"
)

var ctx = context.Background()
var now = time.Now()

type UrlInfo struct {
	ShortURL  string `json:"shortUrl"`
	Timestamp int64  `json:"timestamp"`
}

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

		urlInfo := &UrlInfo{
			ShortURL:  shortenedUrl,
			Timestamp: now.UnixNano(),
		}

		urlJson, err := json.Marshal(urlInfo)
		if err != nil {
			log.Fatal("unable to construct json")
			errorFlag = true
		}
		if errorFlag {
			return c.SendStatus(500)
		}

		err = rdb.Publish(ctx, "pubsub:dev117uday", urlJson).Err()
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

	app.Listen("127.0.0.1:3000")

}

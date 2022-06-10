# Mirko Drotschmann History Discord Bot

This repository contains the code for the [Mirko Drotschmann](https://www.youtube.com/c/MrWissen2go) discord bot. 
This is a bot to send random history facts in your discord channels.

## Features
Mirko Drotschmann's prefix is `!`. For every command (1) a `/` command is also provided.

The Mirko Drotschmann bot has the following features:
* history - send a random history fact in the current channel.

## Bundled Bot

* `App.kt` includes a basic bot which uses environment variables (or variables in a `.env` file) for the testing guild
  ID (`TEST_SERVER`) and the bot's token (`TOKEN`). You can specify these either directly as environment variables, or
  as `KEY=value` pairs in a file named `.env`. This file also includes some example code that shows one potential way 
  of providing different command prefixes for different servers.
* `TestExtension.kt` includes a simple example extension that creates a `slap` command. This command works as both a
  message command and slash command, and allows you to slap other users with whatever you wish, defaulting to a
  `large, smelly trout`.

To test the bot, we recommend using a `.env` file that looks like the following:

```dotenv
TOKEN=abc...
SERVER_ID=123...
MONGO_URL=mongodb://...
MONGO_DATABASE=data...
MONGO_COLLECTION_NAME=history...
```

Create this file, fill it out, and run the `run` gradle task for testing in development.

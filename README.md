# Seller AI Agent

Seller AI Agent is a Spring Boot application that leverages Spring AI and Google Gemini to assist coffee shop staff and customers in ordering drinks, generating sales advertisements, and managing orders. The agent interacts via REST API and integrates with AI models to provide dynamic, context-aware responses for sales and customer support at coffee shops.

## Overview

This application demonstrates how to use Spring AI with Google's Gemini model to generate sales advertisements for children's products targeted at Vietnamese Facebook users. It's a simple command-line application that sends a prompt to the AI model and displays the generated advertisement.

## Features

- Integration with Spring AI framework
- Uses Google's Gemini 2.0 Flash model for text generation
- Configurable AI model parameters

## Technology Stack

- Java 21
- Spring Boot
- Spring AI
- Google Gemini 2.0 Flash (via OpenAI-compatible API)
- PostgreSQL (with pgvector for vector search)
- Maven
- Docker (optional, for running locally)

## Prerequisites

- Java 21
- Maven
- OpenAI API key (set as environment variable)
- PostgreSQL (with pgvector for vector search)

## Configuration

The application is configured to use Google's Gemini model through the OpenAI-compatible API. Configuration is stored in `application.yml`:

```yaml
spring:
  ai:
    openai:
      api-key: ${OPENAI_API_KEY}
      base-url: https://generativelanguage.googleapis.com/v1beta/openai
      chat:
        completions-path: /chat/completions
        options:
          model: gemini-2.0-flash-exp
      embedding:
        embeddings-path: /embeddings
        options:
          model: embedding-001
```

## Environment Variables

Before running the application, set the following environment variable:

```
OPENAI_API_KEY=your_api_key_here
```

## Building the Application

```bash
mvn clean install
```

## Running the Application

```bash
mvn spring-boot:run
```

## Usage Example

### 1. Start the Application

Build and run the application (ensure PostgreSQL is running and environment variables are set):

```bash
mvn clean spring-boot:run
```

Or, using Docker Compose:

```bash
docker-compose up --build
```

### 2. Example API Request

Send a POST request to the seller assistant endpoint:

```bash
curl -X POST "http://localhost:8080/api/store/assistant?q={query}"
```
Sample request:
```
Hi, I'd like to order a coffee
```

Sample response:
```
Okay, which coffee would you like to order? We have Cappuccino, Latte, Caramel Macchiato, and Espresso.
```

You can also interact with the assistant to:
- Get a list of drinks
- Make a draft bill
- Confirm and process orders
- Get today's weather

## How It Works

The application:

1. Initializes a Spring AI ChatClient with a temperature of 0 (for deterministic outputs)
2. Sets a system prompt that instructs the AI to act as a sales assistant for children's products on Facebook for Vietnamese users
3. Sends a user prompt requesting an advertisement for children's hats for ages 3 months to 4 years
4. Prints the generated advertisement to the console

## Question Tests
1. Hi I'm John your interviewer, can you give me some background about yourself?
2. Tell me about the projects that you have worked on with Java
3. What are your expected benefits if you get this job?
4. When you are available to start?

## License

[Add your license information here]

## Author

[Add author information here]

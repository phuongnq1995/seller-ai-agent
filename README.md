# Advertise AI

A Spring Boot application that leverages Spring AI to generate sales advertisements in Vietnamese using AI models.

## Overview

This application demonstrates how to use Spring AI with Google's Gemini model to generate sales advertisements for children's products targeted at Vietnamese Facebook users. It's a simple command-line application that sends a prompt to the AI model and displays the generated advertisement.

## Features

- Integration with Spring AI framework
- Uses Google's Gemini 2.0 Flash model for text generation
- Generates sales advertisements in Vietnamese
- Configurable AI model parameters

## Prerequisites

- Java 21
- Maven
- OpenAI API key (set as environment variable)

## Configuration

The application is configured to use Google's Gemini model through the OpenAI-compatible API. Configuration is stored in `application.yml`:

```yaml
spring:
  application:
    name: advertise-ai
  ai:
    openai:
      api-key: ${OPENAI_API_KEY}
      base-url: https://generativelanguage.googleapis.com/v1beta/openai
      chat:
        completions-path: /chat/completions
        options:
          model: gemini-2.0-flash-exp
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

Or after building:

```bash
java -jar target/advertise-ai-0.0.1-SNAPSHOT.jar
```

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

## Technologies

- Spring Boot 3.4.4
- Spring AI 1.0.0-M6
- Java 21

## License

[Add your license information here]

## Author

[Add author information here]



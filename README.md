# Retack Logger SDK

A simple logger SDK that logs errors into retack.ai.

## Overview

The Retack Logger SDK is a Java library that provides an easy way to log errors and send them to the retack.ai platform. This SDK simplifies the process of integrating error logging into your Java applications.

## Installation

To use the Retack Logger SDK in your project, add the following dependency to your `pom.xml` file:

```xml
    <dependency>
        <groupId>ai.retack</groupId>
        <artifactId>retack-logger</artifactId>
        <version>1.0.0</version>
    </dependency>
```

## Usage

To use the Retack Logger SDK in your project, follow these steps:

1. Initialize the Java Logger:

```java
private static final Logger logger = Logger.getLogger(HelloWorld.class.getName());
```

2. Initialize the Retack Logger SDK with ENV key:
```java
errorLoggingHandler = new ErrorLoggingHandler("your-env-key");
```

3. Add the error logging handler to the logger:
```java
logger.addHandler(errorLoggingHandler);
```

4. Log an error:
```java
logger.log(Level.SEVERE, "An error occurred", exception);
```

## License

This project is licensed under the MIT License. See the `LICENSE` file for more details.
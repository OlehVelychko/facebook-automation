# Facebook Automation

## Overview

This project is part of a technical task for G&M Development. The objective is to create a script that automates the login process to Facebook and extracts the profile photo.

## Key Features

- **Logging**: Logs all key actions and errors into a file named `out.log` for better traceability and debugging.
- **Manual reCAPTCHA Handling**: Pauses the script to allow manual solving of reCAPTCHA if detected.
- **Automation**: Automates login using credentials from a configuration file and extracts the profile photo URL to download the image.

## Technologies

This project is written in Java and utilizes the following technologies:

- **Selenium WebDriver**: For browser automation.
- **Logback and SLF4J**: For logging functionality.
- **Maven**: For dependency management and building the project.

## Current Status

The script successfully implements the basic functionality as defined in the technical task. It includes placeholders for new classes to support better architecture and scalability for future improvements.

## Instructions

### 1. Install Maven

Ensure Maven is installed on your system. Verify by running:
```sh
mvn -v
```

### 2. Clone or Download the Project

Clone the repository or download the project files, then navigate to the project directory:
```sh
cd /path/to/facebook-automation
```

### 3. Configure the Credentials

Edit the `config.properties` file located in `src/main/resources` to provide your Facebook login credentials:
```properties
facebook.email=your_email@example.com
facebook.password=your_password  ###
```

### 4. Build the JAR File

Run the following command to build the JAR file:
```sh
mvn clean package
```

The generated JAR file will be located in the target/ directory:
target/facebook-automation-1.0-SNAPSHOT-jar-with-dependencies.jar

### 5. Run the JAR File

Use the following command to execute the script:
```sh
java -jar target/facebook-automation-1.0-SNAPSHOT-jar-with-dependencies.jar
```

Ensure the chromedriver executable is available on your system, and its path is correctly set in the code or System.setProperty.

## Future Improvements
* Refactor the code for better modularity and reusability.
* Add unit tests for improved reliability.
* Implement advanced reCAPTCHA handling using third-party services.
* Improve error handling and robustness against UI changes on Facebook.

This script is a functional starting point for automating Facebook login and profile photo extraction, with room for future development.

G&M Development Technical Task:

The task was to create a script for automating the login process to Facebook and extracting the profile photo. The script needed to include:
	•	Handling reCAPTCHA manually or programmatically.
	•	Redirecting logs to a file named out.log.

——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

Technologies and Programming Language

This project is written in Java and uses the following technologies:
	•	Selenium WebDriver for browser automation.
	•	Logback and SLF4J for logging functionality.
	•	Maven for dependency management and building the project.

——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

Current Status and Architecture

The script successfully achieves the basic functionality defined in the technical task.
However, it is not optimized and can be improved in the future.
To support better architecture and scalability, the project includes placeholders for new classes, providing a framework for further development.

——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

Key Features Implemented

	1.	Logging:
	•	All key actions and errors are logged to a file named out.log.
	•	This ensures better traceability and debugging capabilities.
	2.	Manual reCAPTCHA Handling:
	•	If a reCAPTCHA is detected during the login process, the script pauses to allow the user to solve it manually.
	3.	Automation:
	•	Automates the login process using provided credentials from a configuration file.
	•	Extracts the profile photo URL and downloads the image.

——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

Instructions for Testing the Script

To test and use the script, follow the steps below:

	1.	Install Maven
Ensure Maven is installed on your system. Verify by running:

mvn -v

	2.	Clone or Download the Project
Get the project files and navigate to the project directory:

cd /path/to/facebook-automation

	3.	Configure the Credentials
Edit the config.properties file located in src/main/resources and provide your Facebook login credentials:

facebook.email=your_email@example.com
facebook.password=your_password

	4.	Build the JAR File
Run the following command to build the JAR file:

mvn clean package

The generated JAR file will be located in the target/ directory:

target/facebook-automation-1.0-SNAPSHOT-jar-with-dependencies.jar

	5.	Run the JAR File
Use the following command to execute the script:

java -jar target/facebook-automation-1.0-SNAPSHOT-jar-with-dependencies.jar

Ensure the chromedriver executable is available on your system, and its path is correctly set in the code or System.setProperty.

——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

Future Improvements

While the script meets the current requirements, the following enhancements can be considered for future iterations:
	•	Refactoring the code for better modularity and reusability.
	•	Adding unit tests for improved reliability.
	•	Implementing a more advanced handling of reCAPTCHA using third-party services.
	•	Improving error handling and robustness against UI changes on Facebook.

——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————

This script is a functional starting point for automating Facebook login and profile photo extraction, with room for future development.

TestCase :
https://docs.google.com/spreadsheets/d/1HXb5DLRzLOL-kWwRqxHVg-43aDwis0hcODhdLyP8Zmw/edit?usp=sharing

Example Automation Test Script Mobile Browser : https://github.com/okiabrian123/Automation-Test-Mobile-Browser

## Automation Test Scripts
This project contains automation test scripts using Selenium WebDriver. The framework supports multiple browsers such as Chrome, Firefox, and Safari, and can be run using Maven.

## Prerequisites
Before running the tests, ensure that you have the following installed:

**Java Development Kit (JDK) 11+**
**Maven**
**Git**
**Getting Started**
**Clone the Repository:**

```bash
git clone https://github.com/okiabrian123/automation-test-scripts.git
cd automation-test-scripts
```
--------------
## Install Dependencies:

Maven will automatically download the required dependencies when you run the tests. However, you can also install them manually:

```bash
mvn clean install
```

--------------
## Running Tests
You can run the tests on different browsers (Chrome, Firefox, Safari) by specifying the browser in the command line.

### Running on Safari

```bash
mvn test -Dbrowser=safari
```
### Running on Chrome
```bash
mvn test -Dbrowser=chrome
```
### Running on Firefox
```bash
mvn test -Dbrowser=firefox
```

By default, if no browser is specified, the tests will run on Chrome.

## Project Structure
The project structure is as follows:

```bash
.
├── pom.xml                # Maven configuration file
├── src
│   └── test
│       ├── java           # Test scripts written in Java
│       └── resources      # Test resources and configuration files
└── README.md              # Project documentation
````
## Maven Profiles
You can also use predefined Maven profiles to run tests in specific environments or configurations.

Example Profile Usage:
```bash
mvn test -Pios
mvn test -Pchrome
mvn test -Pfirefox
```

## WebDriver Setup
This project uses WebDriverManager to automatically manage the browser drivers.

## Note(Safari)
need to allow remote automation in develop settting.
checklist : allow remote automation

Contributions
Feel free to fork the repository and submit pull requests if you'd like to contribute. Issues and feature requests are welcome!

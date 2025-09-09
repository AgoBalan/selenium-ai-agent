# selenium-ai-agent

> **Note:** This entire repository, including all code, configuration, and documentation, was created autonomously by GitHub Copilot (AI Agent) on September 8, 2025. Every commit, file structure decision, and documentation was generated through AI assistance, demonstrating the capabilities of AI in software development.

*****Attached Claude Connectors screenshot and config json for reference**********

A comprehensive test automation framework combining Selenium WebDriver with AI Agent testing capabilities.

## AI Agent Creation Log

### Repository Creation Timeline
1. Initial repository creation by AI Agent
2. Project structure setup and base files creation
3. Implementation of test framework components
4. Configuration of Selenium WebDriver integration
5. Documentation generation and updates

### AI-Generated Components
- Complete project structure design
- Maven configuration (`pom.xml`)
- Base test framework implementation
- Page Object Model setup
- Test runner configuration
- Cucumber feature implementation
- Comprehensive documentation

All commits in this repository have been made through AI Agent assistance, showcasing the potential of AI-driven software development.

## Project Structure

```
selenium-ai-agent/
├── src/
│   ├── main/java/com/test/automation/
│   │   ├── base/
│   │   │   ├── BasePage.java
│   │   │   └── TestBase.java
│   │   ├── pages/
│   │   │   └── LoginPage.java
│   │   └── utils/
│   │       └── ExcelUtils.java
│   └── test/
│       ├── java/com/test/automation/
│       │   ├── base/
│       │   │   └── TestContext.java
│       │   ├── runners/
│       │   │   └── TestRunner.java
│       │   └── stepdefinitions/
│       │       └── LoginSteps.java
│       └── resources/
│           └── features/
│               └── login.feature
└── pom.xml
```

## Features

- Page Object Model design pattern
- Cucumber BDD framework for behavior-driven development
- Selenium WebDriver 4.12.1 integration
- Screenshot capture on test failure
- Detailed logging system
- Excel data handling utilities
- Support for multiple browsers (Chrome, Firefox, Edge)
- AI Agent testing capabilities

## Prerequisites

- Java 21 or higher
- Maven 3.8 or higher
- Chrome/Firefox/Edge browser installed
- Git for version control

## Setup

1. Clone the repository:
```bash
git clone https://github.com/AgoBalan/selenium-ai-agent.git
```

2. Navigate to the project directory:
```bash
cd selenium-ai-agent
```

3. Install dependencies:
```bash
mvn clean install
```

## Running Tests

To run all tests:
```bash
mvn test
```

To run specific test scenarios using tags:
```bash
mvn test -Dcucumber.filter.tags="@your-tag"
```

## Configuration

Browser configuration can be modified using system properties:
```bash
mvn test -Dbrowser=chrome
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
```

## Project Components

### Base Classes
- `BasePage.java`: Core web element interactions and common utilities
- `TestBase.java`: Test configuration and setup
- `TestContext.java`: Test execution context management

### Page Objects
- `LoginPage.java`: Login page elements and actions

### Utilities
- `ExcelUtils.java`: Excel data handling and manipulation

### Test Runner
- `TestRunner.java`: Cucumber test execution configuration

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License.

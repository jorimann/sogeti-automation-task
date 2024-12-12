# Practical Task for Sogeti

This repository demonstrates an automated testing framework as part of a practical task for Sogeti.

## Key Features
- **Page-Object Model (POM)** structure for better maintainability and scalability
- **Support for both UI and API tests**:
   - UI tests are located in 'ui' package.
   - API tests are located in 'api' package.
- **Comprehensive test reports** using Allure.
- **Web UI automation** using Playwright.
- **API Testing** implemented with RestAssured.
- **Parallel test execution** with JUnit5 built-in support.

## Planned Improvements
Below are the planned improvements that will further enhance the framework’s capabilities:
- Implement a unified strategy for managing locators (baed on role and dynamical waiting for state).
- Enhance handling of page load and element availability.
- Add configurable support for multiple environments such as development (dev) and staging (stage)
- Enable configuration for different browsers and devices.
- Optimize the framework for screens on mobile phones and tablets.
- Investigate approaches to block/stub some network request to speed up pages load and make tests more stable.

---

## Installation and Execution (macOS/Unix)

### Prerequisites:
Before running the project, ensure the following tools are installed on your system:
- **Git**
- **Curl** (only for package installation)
- **Homebrew** (only for package installation)
1. Open a terminal and navigate to the desired working directory.
2. Clone the repository:
   ```bash
   git clone https://github.com/jorimann/sogeti-automation-task.git
3. Navigate to the cloned repository:
   ```bash
   cd sogeti-automation-task
4. Install Homebrew (if not already installed):
   ```bash
   /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
5. Install Java 17 using Homebrew (if not already installed):
   ```bash
   brew install openjdk@17
6. Determine the Java 17 home path. Typically after installation via Homebrew, the path will be:
   ```bash
   /opt/homebrew/opt/openjdk@17/libexec/openjdk.jdk/Contents/Home
7. Install Gradle (if not already installed):
   ```bash
   brew install gradle
8. Run the project:
   ```bash
   gradle test allureServe --continue -Dorg.gradle.java.home=/opt/homebrew/opt/openjdk@17/libexec/openjdk.jdk/Contents/Home

---

## Installation and Execution (Windows)
1. Download and copy _win_install_prerequesites.bat_ file to work directory. It will install git, gradle, java 17 and downloads repository
2. Open a terminal with Administrator rights and navigate to the working directory.
3. Execute _win_install_prerequesites.bat_
4. After installation, navigate to the _sogeti_automation_task_ folder:
   ```bash
   cd sogeti-automation-task
5. Run the project:
   ```bash
   c:\Gradle\gradle\bin\gradle test allureServe --continue
   
## Additional Resources
* [Gradle Documentation](https://gradle.org/docs)
* [Allure Framework](https://docs.qameta.io/allure/)
* [Playwright](https://playwright.dev/)
* [RestAssured](https://rest-assured.io/)
* [JUnit5](https://junit.org/junit5/)

## License
This project is provided as part of a practical task and is not intended for commercial use.
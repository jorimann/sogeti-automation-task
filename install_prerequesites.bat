@echo off
REM Set variables for paths
set "JAVA_HOME=C:\OpenJDK\jdk-17"
set "GRADLE_HOME=C:\Gradle"
set "PATH=%JAVA_HOME%\bin;%GRADLE_HOME%\bin;%ProgramFiles%\Git\bin;%PATH%"

REM Step 2: Install Git
echo Installing Git...
powershell -Command "Invoke-WebRequest -Uri https://github.com/git-for-windows/git/releases/download/v2.47.1.windows.1/Git-2.47.1-64-bit.exe -OutFile git-installer.exe"
start /wait git-installer.exe /VERYSILENT /NORESTART
del git-installer.exe
echo Git installation completed.

REM Step 3: Install OpenJDK 17
echo Installing OpenJDK 17...
powershell -Command "Invoke-WebRequest -Uri https://builds.openlogic.com/downloadJDK/openlogic-openjdk/17.0.13+11/openlogic-openjdk-17.0.13+11-windows-x64.zip -OutFile openjdk17.zip"
mkdir C:\OpenJDK
powershell -Command "Expand-Archive -Path openjdk17.zip -DestinationPath C:\OpenJDK"
move C:\OpenJDK\jdk-17* C:\OpenJDK\jdk-17
del openjdk17.zip
echo OpenJDK installation completed.

REM Step 4: Install Gradle
echo Installing Gradle...
powershell -Command "Invoke-WebRequest -Uri https://services.gradle.org/distributions/gradle-8.8-bin.zip -OutFile gradle.zip"
mkdir C:\Gradle
powershell -Command "Expand-Archive -Path gradle.zip -DestinationPath C:\Gradle"
move C:\Gradle\gradle-* C:\Gradle\gradle
del gradle.zip
echo Gradle installation completed.

REM Verify installations
echo Verifying Git version...
git --version
if %errorlevel% neq 0 (
    echo Git is not installed correctly. Exiting...
    exit /b 1
)

echo Verifying Java version...
"%JAVA_HOME%\bin\java.exe" -version
if %errorlevel% neq 0 (
    echo Java is not installed correctly. Exiting...
    exit /b 1
)

echo Verifying Gradle version...
gradle -v
if %errorlevel% neq 0 (
    echo Gradle is not installed correctly. Exiting...
    exit /b 1
)

REM Completion message
echo Setup completed successfully.
pause

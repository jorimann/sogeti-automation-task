# sogeti-automation-task
# Prerequesites:

## For Macos/Unix:
1. Make sure Homebrew installed:
   /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
2. download java 17: 
   brew install openjdk@17
3. Notice Java Home (by default after brew installation:) /opt/homebrew/opt/openjdk@17/libexec/openjdk.jdk/Contents/Home
4. Install gradle:
   brew install gradle
5. Run script:
   gradle test allureServe --continue -Dorg.gradle.java.home=/opt/homebrew/opt/openjdk@17/libexec/openjdk.jdk/Contents/Home
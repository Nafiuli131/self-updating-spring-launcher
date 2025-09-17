# Spring Boot JAR Launcher

A lightweight Spring Boot launcher application that manages another Spring Boot application JAR.
It allows you to upload a new JAR via a REST API, automatically replaces the old one, and restarts
the application with the new JAR.

This is useful for scenarios where you want to update and redeploy a Spring Boot JAR dynamically
without manually restarting or replacing files.

---

## 🚀 Features

- Runs as a **launcher service** that never gets replaced.
- Provides a **REST API** to upload a new application JAR.
- Replaces the old JAR (`app.jar`) with the new one.
- Stops the running application process and restarts it with the new JAR.
- Works for the first startup (when no `app.jar` exists) as well as subsequent updates.

---

## ⚙️ How It Works

1. Start the **launcher**:
   ```bash
   java -jar launcher.jar


Upload your first application JAR:

curl -F "file=@myapp.jar" http://localhost:8080/upload


The old application process will be terminated.

The new JAR will replace app.jar.

Then start the application app.jar and you'll see the app.jar is created in the directory



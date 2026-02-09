# SuperHeroApp

An Android application that displays information about superheroes using the [SuperHero API](https://akabab.github.io/superhero-api/api/).

## 🚀 Overview

SuperHeroApp is a modern Android application built with Jetpack Compose, showcasing a list of superheroes with their details fetched from a remote API. It demonstrates the use of Ktor for networking, Koin for dependency injection, and follow Clean Architecture principles.

## 🛠 Tech Stack

- **Language:** [Kotlin](https://kotlinlang.org/)
- **UI Framework:** [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Dependency Injection:** [Koin](https://insert-koin.io/)
- **Networking:** [Ktor](https://ktor.io/)
- **Serialization:** [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization)
- **Architecture:** MVVM (Model-View-ViewModel)
- **Build System:** Gradle (Kotlin DSL)

## 📁 Project Structure

```text
app/src/main/java/com/jsaldana/superheroapp/
├── data/
│   ├── dto/           # Data Transfer Objects for API responses
│   └── model/         # Domain models
├── modules/           # Koin DI modules (AppModule, NetworkModule)
├── repository/        # Data repositories
├── service/           # API service implementation (Ktor)
├── ui/theme/          # Compose theme definitions (Color, Type, Theme)
├── MainActivity.kt    # Main entry point activity
├── MainViewModel.kt   # ViewModel for the main screen
└── SuperHeroApplication.kt # Application class for Koin initialization
```

## 📋 Requirements

- Android Studio Ladybug | 2024.2.1 or newer (recommended)
- Android SDK 31+ (Min SDK: 31, Target SDK: 36)
- JDK 11 or newer

## ⚙️ Setup & Run

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/jsaldana/superheroapp.git
    cd superheroapp
    ```
2.  **Open in Android Studio:**
    - Select `File > Open` and navigate to the project directory.
3.  **Sync Gradle:**
    - Android Studio should automatically prompt to sync Gradle. If not, go to `File > Sync Project with Gradle Files`.
4.  **Run the app:**
    - Select a physical device or emulator and click the **Run** button (green arrow) in Android Studio.

## 📜 Available Scripts

The project uses Gradle. Common commands (using the wrapper):

- **Build project:**
  ```bash
  ./gradlew build
  ```
- **Run Unit Tests:**
  ```bash
  ./gradlew test
  ```
- **Run Instrumented Tests:**
  ```bash
  ./gradlew connectedAndroidTest
  ```
- **Clean Build:**
  ```bash
  ./gradlew clean
  ```

## 🔐 Environment Variables

No specific environment variables are required for basic functionality. The application uses a public API.

> [!TODO]
> If future integrations (like Facebook SDK or Unity Ads) require API keys, they should be documented here.

## 🧪 Tests

- **Unit Tests:** Located in `app/src/test/java/`.
- **Instrumented Tests:** Located in `app/src/androidTest/java/`.

To run tests from Android Studio, right-click on the `test` or `androidTest` folder and select **Run 'Tests in...'**.

## 📄 License

This project is licensed under the [MIT License](LICENSE) (Verify and update as needed).

---
*Note: This project is currently in development. Some features might be partially implemented.*

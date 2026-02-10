# SuperHeroApp

An Android application that displays information about superheroes using the [SuperHero API](https://akabab.github.io/superhero-api/api/).

## 🚀 Overview

SuperHeroApp is a modern Android application built with Jetpack Compose, showcasing a list of superheroes with their details fetched from a remote API. It demonstrates the use of Ktor for networking, Koin for dependency injection, and follows MVVM (Model-View-ViewModel) architecture.

## 🛠 Tech Stack

- **Language:** [Kotlin](https://kotlinlang.org/)
- **UI Framework:** [Jetpack Compose](https://developer.android.com/jetpack/compose) with Material 3
- **Dependency Injection:** [Koin](https://insert-koin.io/)
- **Networking:** [Ktor](https://ktor.io/) with OkHttp engine
- **Image Loading:** [Coil](https://coil-kt.github.io/coil/)
- **Serialization:** [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization)
- **Architecture:** MVVM (Model-View-ViewModel)
- **Build System:** Gradle (Kotlin DSL)
- **Other Integrations:** Kotzilla SDK, Facebook SDK, Unity Ads

## 📁 Project Structure

```text
app/src/main/java/com/jsaldana/superheroapp/
├── data/
│   ├── dto/           # Data Transfer Objects for API responses
│   └── model/         # Domain models used in UI
├── modules/           # Koin DI modules
│   ├── AppModule.kt   # Repositories and ViewModels definitions
│   └── NetworkModule.kt # Ktor HttpClient and API service configuration
├── repository/        # Data repositories (using Result wrapper for error handling)
├── service/           # API service interface and implementation
├── ui/                # UI Layer
│   ├── navigation/    # Navigation graph and destinations
│   ├── screens/       # Composable screens (Home, Detail, Search)
│   │   ├── components/# Reusable UI components
│   │   └── home/      # Home screen specific components
│   └── theme/         # Compose theme definitions (Color, Type, Theme)
├── MainActivity.kt    # Main entry point activity
├── MainViewModel.kt   # Shared ViewModel for state management
└── SuperHeroApplication.kt # Application class for Koin initialization
```

## 📋 Requirements

- **Android Studio:** Ladybug | 2024.2.1 or newer
- **Android SDK:** Min SDK 31, Target SDK 36, Compile SDK 36
- **JDK:** 11
- **Gradle:** Kotlin DSL (`*.gradle.kts`)

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

The project uses the Gradle wrapper. Common commands:

- **Build project:**
  ```bash
  ./gradlew assembleDebug
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

## 🔐 Environment Variables & Configuration

The application uses the public [SuperHero API](https://akabab.github.io/superhero-api/api/). No API key is currently required for basic functionality.

- **Networking:** Configured in `NetworkModule.kt` using Ktor's `ContentNegotiation` with JSON settings: `ignoreUnknownKeys = true`, `isLenient = true`, `explicitNulls = false`.
- **DI:** Koin modules are defined in `com.jsaldana.superheroapp.modules` and initialized in `SuperHeroApplication.kt`.

> [!IMPORTANT]
> - Mapping logic is typically inside DTOs via `toModel()` extension or member functions.
> - Repositories use the `Result` wrapper to handle exceptions and return success/failure states.

> [!TODO]
> - Implement/Verify Facebook SDK and Unity Ads initialization.
> - Add API keys to `local.properties` or environment variables if needed for protected endpoints.
> - Create a `LICENSE` file (currently defaults to MIT in documentation).

## 🧪 Tests

- **Unit Tests:** Located in `app/src/test/java/`. Uses JUnit 4. `runBlocking` is used for testing `suspend` functions.
- **Instrumented Tests:** Located in `app/src/androidTest/java/`.

To run tests from Android Studio, right-click on the `test` or `androidTest` folder and select **Run 'Tests in...'**.

## 📄 License

This project is licensed under the [MIT License](LICENSE) (Verify and update as needed).

---
*Note: This project is currently in development. Some features might be partially implemented.*

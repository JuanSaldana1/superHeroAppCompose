### SuperHeroApp Project Guidelines

#### 1. Build and Configuration Instructions
- **SDK Versions**: Compile and Target SDK 36, Minimum SDK 31.
- **Gradle**: Uses Kotlin DSL (`*.gradle.kts`).
- **Dependency Management**: Dependencies are managed via Version Catalog (`gradle/libs.versions.toml`).
- **Dependency Injection**: Koin is used for DI. Modules are defined in `com.jsaldana.superheroapp.modules`.
  - `NetworkModule`: Configures `HttpClient` (Ktor) and API services.
  - `AppModule`: Defines repositories and ViewModels.
- **Networking**: Ktor with `OkHttp` engine and `Kotlinx Serialization`.
  - JSON configuration: `ignoreUnknownKeys = true`, `isLenient = true`, `explicitNulls = false`.
- **UI**: Jetpack Compose with Material 3.

#### 2. Testing Information
- **Configuration**: Standard JUnit 4 is used for unit testing.
- **Running Tests**: Execute `./gradlew test` from the root directory to run all unit tests.
- **Adding Tests**:
  - Unit tests should be placed in `app/src/test/java/`.
  - Use `runBlocking` for testing `suspend` functions.
  - For components relying on `SuperHeroAPIService`, use fakes or mocks as shown in the example below.

##### Test Example (demonstration)
```kotlin
package com.jsaldana.superheroapp.repository

import com.jsaldana.superheroapp.data.dto.*
import com.jsaldana.superheroapp.service.SuperHeroAPIService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SuperHeroRepositoryTest {
    private class FakeSuperHeroAPIService : SuperHeroAPIService {
        override suspend fun getAllSuperheroes(): List<SuperHeroDTO> {
            return listOf(/* ... mock data ... */)
        }
    }

    @Test
    fun `test mapping logic`() = runBlocking {
        val repository = SuperHeroRepository(FakeSuperHeroAPIService())
        val result = repository.getAllSuperheroes()
        assertTrue(result.isSuccess)
    }
}
```

#### 3. Additional Development Information
- **Architecture**: Follows MVVM (Model-View-ViewModel).
  - **DTOs**: Data Transfer Objects in `com.jsaldana.superheroapp.data.dto`.
  - **Models**: Domain models in `com.jsaldana.superheroapp.data.model`.
  - **Mappers**: Mapping logic is typically inside DTOs via `toModel()` extension or member functions.
- **Code Style**:
  - Use Kotlin standard coding conventions.
  - Prefer `val` over `var`.
  - Use Koin's `viewModel { ... }` for ViewModel injection.
  - In Compose, use `koinViewModel()` to obtain ViewModels.
- **Logging**: Ktor network logging is configured in `NetworkModule` using `HttpLogging` tag.
- **Error Handling**: Use `Result` wrapper in Repositories to handle exceptions and return success/failure states.

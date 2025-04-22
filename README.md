# atlys android test
A sample project for where you can see trending movies.

## Table of Contents

- [Features](#features)
- [Libraries Used](#libraries-used)
- [Architecture](#architecture)
- [Author](#author)

## Features

-   **Trending Movies:** Displays a list of the current trending movies.
-   **Clean Architecture:** The project is structured using clean architecture principles, promoting modularity and testability.
-   **Jetpack Libraries:** Utilizes popular Jetpack libraries for enhanced functionality and best practices.
- **Modern Android development:** The app is build using the latest and most popular technologies.


## Libraries Used

This project leverages the following powerful libraries:
-   **Jetpack Compose:**
    -   `androidx.compose.ui`: The core UI library for building declarative UIs.
    -   `androidx.compose.ui.graphics`:  For handling graphics and drawing.
    -   `androidx.compose.ui.tooling.preview`: Enables previewing composable functions in Android Studio.
    -   `androidx.compose.material3`: For implementing Material Design 3 components.
    - `androidx.activity.compose`: Integration of compose with the activity.
    - `androidx.navigation.compose`: navigation between the composables.
    -   **Why Compose?** Compose is Android's modern toolkit for building native UI. It simplifies UI development and provides a declarative way to define user interfaces.
-   **Hilt:**
    -   `com.google.dagger:hilt-android`: For dependency injection.
    -   `androidx.hilt:hilt-navigation-compose`: Integration of hilt with compose navigation.
    - **Why Hilt?** Hilt provides a standard way to incorporate Dagger dependency injection into Android apps.
-   **Retrofit:**
    -   `com.squareup.retrofit2:retrofit`: For making network requests.
    -   `com.squareup.retrofit2:converter-gson`: For JSON parsing and serialization.
    - **Why Retrofit?** Retrofit is a popular, type-safe HTTP client for Android, making API interactions simple.
- **OkHttp**
    - `com.squareup.okhttp3:logging-interceptor` for logging the network request.
    - **Why OkHttp?** Because it is the best option for network requests.
- **Gson**
    - `com.google.code.gson:gson`: for converting java objects into JSON objects.
    - **Why Gson?** to convert the response into data objects.
-   **Room:**
    -   `androidx.room:room-ktx`: For local data persistence and caching.
    -   **Why Room?** Room is the recommended persistence library for Android. It provides an abstraction layer over SQLite, making database interactions easier.
- **KSP:**
    - `com.google.devtools.ksp:symbol-processing-api`: For generating code.
    - **Why KSP?** for having a faster annotation process.
- **Desugar jdk libs**
    - `com.android.tools:desugar_jdk_libs`: To use the newest features from java 8.
    - **Why desugar jdk libs?** to use new features.

## Architecture

This application follows a clean architecture approach. Key layers include:

-   **Presentation:** The UI layer built using Jetpack Compose.
-   **Domain:** Contains business logic and use cases.
-   **Data:** Handles data retrieval and persistence, both remote and local.
- **DI**: Uses Hilt for dependency injection.

## Author ✍️

- SurajKGoyal

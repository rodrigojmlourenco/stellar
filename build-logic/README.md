# Convention Plugins

Based on [NowInAndroid](https://github.com/android/nowinandroid/tree/main/build-logic)

Supported plugins are divided into:

**Application plugins:**

- `stellar.android.application`: The base Android application setup
- `stellar.android.application.compose`: Adds compose to the application
- `stellar.android.application.jacoco`: Adds JaCoCo to the application

**Library plugins:**

- `stellar.android.library`: Android library
- `stellar.kotlin.library`: Kotlin vanilla library

**Add-ons plugins:**

- `stellar.android.with.jacoco`: Adds JaCoCo to an Android library
- `stellar.android.with.compose`: Adds Androidx Compose to an Android Library
- `stellar.android.with.hilt`: Adds Dagger Hilt to an Android Library
- `stellar.android.with.room`: Adds Androidx Room to an Android Library
- `stellar.with.lint`: Adds Android to any Library type
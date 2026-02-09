import java.util.Properties

plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.compose)
}

android {
	namespace = "com.jsaldana.superheroapp"
	compileSdk {
		version = release(36)
	}

	defaultConfig {
		applicationId = "com.jsaldana.superheroapp"
		minSdk = 31
		targetSdk = 36
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		val keystoreFile = project.rootProject.file("local.properties")
		val properties = Properties()
		properties.load(keystoreFile.inputStream())

		//return empty key in case something goes wrong
		val apiKey = properties.getProperty("superHeroAPIKey") ?: ""
		buildConfigField(
			type = "String",
			name = "API_KEY",
			value = apiKey
		)
	}

	buildFeatures {
		compose = true
		buildConfig = true
	}

	composeOptions {
		kotlinCompilerExtensionVersion = libs.versions.composeBom.toString()
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}

	/*lint {
		baseline = file("lint-baseline.xml")
	}*/
}

dependencies {
	androidTestImplementation(libs.androidx.compose.ui.test.junit4)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	debugImplementation(libs.androidx.compose.ui.test.manifest)
	debugImplementation(libs.androidx.compose.ui.tooling)

	implementation(libs.androidx.activity.compose)
	implementation(libs.androidx.compose.material3)
	implementation(libs.androidx.compose.ui)
	implementation(libs.androidx.compose.ui.graphics)
	implementation(libs.androidx.compose.ui.tooling.preview)
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)

	// Facebook
	implementation(libs.facebook.android.sdk)

	// KTOR
	implementation(libs.ktor)
	implementation(libs.ktor.client.cio)
	implementation(libs.ktor.client.content.negotiation)
	implementation(libs.ktor.client.core)
	implementation(libs.ktor.client.logging)
	implementation(libs.ktor.client.okhttp)
	implementation(libs.ktor.serialization.kotlinx.json)


	// FROGO-SDK
	// implementation(libs.frogo.android)

	// UNITY3D-ADS
	implementation(libs.unity3D.ads)



	// KOIN
	implementation(libs.koin)
	implementation(libs.koin.compose)
	implementation(libs.koin.core)
	implementation(libs.koin.ktor)

	// KOTZILLA
	implementation(libs.kotzilla.sdk.compose)

	implementation(platform(libs.androidx.compose.bom))

	testImplementation(libs.junit)
}
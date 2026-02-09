plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.compose)
	alias(libs.plugins.kotlin.serialization)
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
	implementation(libs.androidx.activity.compose)
	implementation(libs.androidx.compose.foundation.layout)
	implementation(libs.androidx.compose.material)
	implementation(libs.androidx.compose.material.icons.core)
	implementation(libs.androidx.compose.material.icons.core.android)
	implementation(libs.androidx.compose.material.icons.core.extended)
	implementation(libs.androidx.compose.material3)
	implementation(libs.androidx.compose.material3)
	implementation(libs.androidx.compose.material3.adaptive)
	implementation(libs.androidx.compose.material3.adaptive.android)
	implementation(libs.androidx.compose.material3.adaptive.layout)
	implementation(libs.androidx.compose.material3.adaptive.navigation)
	implementation(libs.androidx.compose.ui)
	implementation(libs.androidx.compose.ui.graphics)
	implementation(libs.androidx.compose.ui.tooling.preview)
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.navigation.safe.args.generator)

	androidTestImplementation(libs.androidx.compose.ui.test.junit4)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	debugImplementation(libs.androidx.compose.ui.test.manifest)
	debugImplementation(libs.androidx.compose.ui.tooling)

	// COIL
	implementation(libs.coil.compose)
	implementation(libs.coil.network.ktor)

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

configurations.all {
	exclude(group = "xmlpull", module = "xmlpull")
	exclude(group = "xpp3", module = "xpp3")
}
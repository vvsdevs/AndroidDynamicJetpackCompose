import com.vanniktech.maven.publish.SonatypeHost
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.vanniktech.maven.publish") version "0.30.0"
    kotlin("kapt")
}

android {
    namespace = "com.android.dynamicjetpackcompose"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.cardview)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Hilt and Retrofit dependencies
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
    implementation(libs.retrofit)
    implementation(libs.gson.converter)
    implementation(libs.accompanist.swiperefresh)
    implementation(libs.coil.compose)
    implementation (libs.androidx.foundation)
    //preferences
    implementation(libs.androidx.datastore.preferences)
}
mavenPublishing {
    // publishing to https://s01.oss.sonatype.org
    publishToMavenCentral(SonatypeHost.S01)
    signAllPublications()
}


mavenPublishing {
    coordinates("com.vvsdevs.AndroidDynamicJetpackCompose", "AndroidDynamicJetpackCompose", "1.4.0")

    pom {
        name.set("remote-compose")
        description.set("Android Dynamic Jetpack Compose is a powerful library that enables dynamic layout rendering based on JSON configurations using Jetpack Compose. This library allows developers to design and update UI elements dynamically without needing to release a new app update, making it a flexible and efficient solution for Android applications.")
        inceptionYear.set("2024")
        url.set("https://github.com/vvsdevs/AndroidDynamicJetpackCompose/")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("vvsdevs")
                name.set("Venky")
                url.set("https://github.com/vvsdevs/")
            }
        }
        scm {
            url.set("https://github.com/vvsdevs/AndroidDynamicJetpackCompose/")
            connection.set("scm:git:git://github.com/vvsdevs/AndroidDynamicJetpackCompose.git")
            developerConnection.set("scm:git:ssh://git@github.com/vvsdevs/AndroidDynamicJetpackCompose.git")
        }
    }
}

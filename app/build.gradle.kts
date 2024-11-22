plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.monosoft.myprofile"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.monosoft.myprofile"
        minSdk = 24
        targetSdk = 34
        versionCode = 8
        versionName = "1.8"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    //Gson + Retrofit
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.retrofit2:retrofit:2.10.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.10.0")

    // MOSHI
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")

    //circle image
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    //glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")

    //lottie
    implementation ("com.airbnb.android:lottie:3.4.0")

    //firebase
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))
    implementation ("com.google.firebase:firebase-messaging")
    implementation ("com.google.firebase:firebase-storage-ktx:20.2.1")
    implementation ("com.google.firebase:firebase-auth-ktx:22.1.1")

    //maps
    implementation ("com.google.android.gms:play-services-maps:19.0.0")

    //shrimer layout android kotlin
    implementation ("com.facebook.shimmer:shimmer:0.5.0")

    //progress bar
    implementation ("com.akexorcist:round-corner-progress-bar:2.2.1")

    //dialogo
    implementation ("com.github.chnouman:AwesomeDialog:1.0.5")

    //Photo view para hacer zoom a imagenes
    implementation ("com.github.chrisbanes:PhotoView:2.3.0")

    //logger para imprimir logs en consola
    implementation ("com.orhanobut:logger:2.2.0")

}

secrets {
    // Optionally specify a different file name containing your secrets.
    // The plugin defaults to "local.properties"
    propertiesFileName = "secrets.properties"

    // A properties file containing default secret values. This file can be
    // checked in version control.
    defaultPropertiesFileName = "local.defaults.properties"

    // Configure which keys should be ignored by the plugin by providing regular expressions.
    // "sdk.dir" is ignored by default.
    ignoreList.add("keyToIgnore") // Ignore the key "keyToIgnore"
    ignoreList.add("sdk.*")       // Ignore all keys matching the regexp "sdk.*"
}


// Allow references to generated code
kapt {
    correctErrorTypes = true
}
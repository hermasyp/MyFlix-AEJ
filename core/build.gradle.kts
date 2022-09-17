plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    compileSdk = AndroidProjectConfig.compileSdk

    defaultConfig {
        minSdk = AndroidProjectConfig.minSdk
        targetSdk = AndroidProjectConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    flavorDimensions += listOf("default")
    productFlavors {
        create("production") {
            dimension = "default"
            buildConfigField("String", "BASE_URL", "\"http://75.101.213.57/\"")
            buildConfigField("String", "APPLICATION_ID", "\"$AndroidProjectConfig.applicationId\"")
        }
    }
}

dependencies {

    api(Libraries.androidxCoreKtx)
    api(Libraries.androidxAppcompat)
    api(Libraries.googleAndroidMaterial)
    api(Libraries.androidxConstraintLayout)
    api(Libraries.swipeRefreshLayout)

    testApi(Libraries.junit)
    androidTestApi(Libraries.androidJunit)
    androidTestApi(Libraries.espressoCore)

    //view model and lifecycle stuffs
    api(Libraries.lifecycleViewModelKtx)
    api(Libraries.lifecycleLiveDataKtx)
    api(Libraries.lifecycleRuntimeKtx)

    api(Libraries.retrofitConverterGson)
    api(Libraries.retrofit2)

    //coroutine
    api(Libraries.coroutineCore)
    api(Libraries.coroutineAndroid)

    //datastore
    api(Libraries.jetpackDatastore)

    //gson
    api(Libraries.gson)

    //koin
    api(Libraries.koinAndroid)

    //coil
    api(Libraries.coil)


    //core module
    api(project(":styling"))

}
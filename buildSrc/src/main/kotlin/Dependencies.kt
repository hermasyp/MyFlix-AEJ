/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

object AndroidProjectConfig {
    const val minSdk = 21
    const val compileSdk = 32
    const val targetSdk = 32
    const val versionCode = 1
    const val versionName = "1.0"
    const val applicationId = "com.catnip.myflix"
}

object Libraries {

    object Versions {
        const val coreKtx = "1.8.0"
        const val appcompat = "1.4.2"
        const val constraintLayout = "2.1.4"
        const val lifecycle = "2.5.0"
        const val googleMaterial = "1.6.1"
        const val gson = "2.9.0"
        const val coroutine = "1.6.1"
        const val retrofit = "2.9.0"
        const val koin = "3.2.0"
        const val chucker = "3.5.2"
        const val room = "2.4.2"
        const val junit = "4.13.2"
        const val androidTestJunit = "1.1.3"
        const val espresso = "3.4.0"
        const val jetpackDatastore = "1.0.0"
        const val androidxActivityKtx = "1.5.0"
        const val coil = "2.1.0"
        const val exoplayer = "2.18.1"
        const val swipeRefreshLayout = "1.1.0"
    }

    const val androidxCoreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val androidxAppcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val androidxConstraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val lifecycleViewModelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleLiveDataKtx =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val googleAndroidMaterial =
        "com.google.android.material:material:${Versions.googleMaterial}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val coroutineAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
    const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
    const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val chucker = "com.github.chuckerteam.chucker:library:${Versions.chucker}"
    const val chuckerNoOp = "com.github.chuckerteam.chucker:library-no-op:${Versions.chucker}"
    const val junit = "junit:junit:${Versions.junit}"
    const val androidJunit = "androidx.test.ext:junit:${Versions.androidTestJunit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val jetpackDatastore =
        "androidx.datastore:datastore-preferences:${Versions.jetpackDatastore}"
    const val coil = "io.coil-kt:coil:${Versions.coil}"
    const val exoplayer = "com.google.android.exoplayer:exoplayer:${Versions.exoplayer}"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"

}
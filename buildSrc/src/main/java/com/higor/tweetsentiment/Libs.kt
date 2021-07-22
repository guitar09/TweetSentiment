package com.higor.tweetsentiment

object Libs {

    object Android {
       const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.Android.APP_COMPAT}"
       const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Android.KOTLIN_VERSION}"
       const val MATERIAL_COMPONENT = "com.google.android.material:material:${Versions.Android.MATERIAL_COMPONENT}"
       const val CORE_KTX = "androidx.core:core-ktx:${Versions.Android.CORE_KTX}"
       const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.Android.CONSTRAINT_LAYOUT}"
       const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Android.KOTLIN_VERSION}"
       const val TOOLS_BUILD_GRADLE = "com.android.tools.build:gradle:${Versions.Android.TOOLS_BUILD_GRADLE}"
       const val LIVE_CYCLE_VIEWMODEL =  "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Android.LIFE_CYCLE}"
       const val LIVE_CYCLE_LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Android.LIFE_CYCLE}"
       const val LIVE_CYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Android.LIFE_CYCLE}"
       const val MULTIDEX = "com.android.support:multidex:${Versions.Android.MULTIDEX}"
    }

    object Async {
        const val COROUTINE_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Async.COROUTINE}"
        const val COROUTINE_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Async.COROUTINE}"
    }

    object DependencyInject{
        const val KOIN_ANDROID = "org.koin:koin-android:${Versions.DependencyInject.KOIN}"
        const val KOIN_VIEWMODEL = "org.koin:koin-androidx-viewmodel:${Versions.DependencyInject.KOIN}"
        const val KOIN_CORE = "org.koin:koin-core:${Versions.DependencyInject.KOIN}"
    }

    object Network {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.Network.RETROFIT}"
        const val RETROFIT_ADAPTER_GSON = "com.squareup.retrofit2:converter-gson:${Versions.Network.RETROFIT}"
        const val OK_HTTP = "com.squareup.okhttp3:okhttp:${Versions.Network.OK_HTTP}"
        const val OK_HTTP_LOGGING = "com.squareup.okhttp3:logging-interceptor:${Versions.Network.OK_LOGGING}"
    }

    object UI {
        const val LOTTIE = "com.airbnb.android:lottie:${Versions.UI.LOTTIE}"
    }

    object Test {
        const val KOIN_TEST = "org.koin:koin-test:${Versions.Test.KOIN_TEST}"
        const val JUNIT = "junit:junit:${Versions.Test.JUNIT}"
        const val EXT_JUNIT = "androidx.test.ext:junit:${Versions.Test.EXT_JUNIT}"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.Test.ESPRESSO_CORE}"
        const val MOCKK = "io.mockk:mockk:${Versions.Test.MOCKK}"
        const val ROBOLECTRIC = "org.robolectric:robolectric:${Versions.Test.ROBOLECTRIC}"
        const val TRUTH = "com.google.truth:truth:${Versions.Test.TRUTH}"
        const val ANDROID_TEST_CORE = "androidx.test:core:${Versions.Test.ANDROID_TEST_CORE}"
        const val ARCH_CORE_TEST = "androidx.arch.core:core-testing:${Versions.Test.ARCH_CORE_TEST}"
        const val COROUTINE_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Test.COROUTINE_TEST}"

    }
}
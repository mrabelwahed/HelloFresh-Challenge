object Versions {
    const val KOTLIN = "1.3.72"
    const val COMPILE_SDK = 28
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 28
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"
    const val RXJAVA_VERSION = "2.2.13"
    const val RX_ANDROID = "2.1.1"
    const val RX_RETROFIT_ADAPTER = "2.6.1"
    const val DAGGER_VERSION = "2.22"
    const val DAGGER_COMPILER = "2.22"
    const val RETROFIT_VERSION = "2.7.1"
    const val GSON_VERSION = "2.8.5"
    const val OKHTTP_LOGGING_INTERCEPTOR_VERSION = "3.12.1"
    const val LIFE_CYCLE_VERSION = "2.2.0"
    const val CARD_VIEW_VERSION = "1.0.0"
    const val RECYCLERVIEW_VERSION = "1.0.0"
    const val CONSTRAINT_LAYOUT = "1.1.3"
    const val ANDROID_GRADLE_VERSION = "4.0.0"
    const val ESPRESSO_VERSION = "3.1.1"
    const val APP_COMPAT_VERSION = "1.1.0"
    const val CORE_KTX = "1.1.0"
    const val ARCH_CORE_TESTING_VER = "2.0.0"
    const val TEST_RUNNER_VER = "1.1.1"
    const val RULES_VER = "1.1.1"
    const val TRUTH_VER = "1.1.0"
    const val JUNIT_EXT_VER = "1.1.0"
    const val MOCKITO = "3.3.1"
    const val RX_IDLER = "0.10.0"
    const val COIL_VER = "0.6.1"
}

object BuildPlugins {
    val androidGradle = "com.android.tools.build:gradle:${Versions.ANDROID_GRADLE_VERSION}"
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
}

object Android {
    val minSDK = Versions.MIN_SDK_VERSION
    val targetSDK = Versions.TARGET_SDK_VERSION
    val versionCode = Versions.VERSION_CODE
    val versionName = Versions.VERSION_NAME
    val compileSDK = Versions.COMPILE_SDK
    val applicationId = "com.onefootball"
}

object Libs {
    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN}"
    val rxVersion = "io.reactivex.rxjava2:rxjava:${Versions.RXJAVA_VERSION}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.RX_ANDROID}"
    val dagger = "com.google.dagger:dagger:${Versions.DAGGER_VERSION}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.DAGGER_COMPILER}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.RECYCLERVIEW_VERSION}"
    val cardview = "androidx.cardview:cardview:${Versions.CARD_VIEW_VERSION}"
    val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    val liveData = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFE_CYCLE_VERSION}"
    val viewModel = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFE_CYCLE_VERSION}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.APP_COMPAT_VERSION}"
    val coreExt = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    val coil = "io.coil-kt:coil:${Versions.COIL_VER}"
}

object TestLibs {
    val junit = "junit:junit:4.12"
    val espressoCore = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_VERSION}"
    val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.ESPRESSO_VERSION}"
    val espressoIntents = "androidx.test.espresso:espresso-intents:${Versions.ESPRESSO_VERSION}"
    val espressoIdlingResource =
        "androidx.test.espresso:espresso-idling-resource:${Versions.ESPRESSO_VERSION}"
    val archCoreTesting = "androidx.arch.core:core-testing:${Versions.ARCH_CORE_TESTING_VER}"
    val testRunner = "androidx.test:runner:${Versions.TEST_RUNNER_VER}"
    val rules = "androidx.test:rules:${Versions.RULES_VER}"
    val truth = "androidx.test.ext:truth:${Versions.TRUTH_VER}"
    val junitExt = "androidx.test.ext:junit:${Versions.JUNIT_EXT_VER}"
    val mockito = "org.mockito:mockito-core:${Versions.MOCKITO}"
    val mockitoAndroid = "org.mockito:mockito-android:${Versions.MOCKITO}"
    val rxIdler = "com.squareup.rx.idler:rx2-idler:${Versions.RX_IDLER}"
}
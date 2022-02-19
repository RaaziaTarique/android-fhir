plugins {
  id(Plugins.BuildPlugins.androidLib)
  id(Plugins.BuildPlugins.benchmark)
  id(Plugins.BuildPlugins.kotlinAndroid)
}

android {
  compileSdk = 31
  compileOptions {
    isCoreLibraryDesugaringEnabled = true
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions { jvmTarget = "1.8" }

  defaultConfig {
    minSdk = Sdk.minSdk
    targetSdk = 31
    testInstrumentationRunnerArguments["androidx.benchmark.profiling.mode"] = "StackSampling"

    testInstrumentationRunnerArguments["androidx.benchmark.output.enable"] = "true"
    if (System.getenv("CI") == "true") {
      testInstrumentationRunnerArguments["androidx.benchmark.suppressErrors"] = "EMULATOR,UNLOCKED"
    }
  }

  testBuildType = "release"
  buildTypes {
    debug {
      isMinifyEnabled = true
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "benchmark-proguard-rules.pro"
      )
    }
    release { isDefault = true }
  }
  packagingOptions {
    resources.excludes.add("META-INF/ASL-2.0.txt")
    resources.excludes.add("META-INF/LGPL-3.0.txt")
    resources.excludes.add("META-INF/AL2.0")
    resources.excludes.add("META-INF/LGPL2.1")
  }
}

dependencies {
  androidTestImplementation(Dependencies.Kotlin.kotlinCoroutinesTest)
  androidTestImplementation(project(mapOf("path" to ":engine")))
  androidTestImplementation(Dependencies.AndroidxTest.runner)
  androidTestImplementation(Dependencies.AndroidxTest.extJunit)
  androidTestImplementation(Dependencies.AndroidxTest.extJunitKtx)
  androidTestImplementation(Dependencies.AndroidxTest.benchmark)

  coreLibraryDesugaring(Dependencies.desugarJdkLibs)
}
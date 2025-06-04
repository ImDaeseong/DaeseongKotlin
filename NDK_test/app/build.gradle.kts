plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.daeseong.ndk_test"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.daeseong.ndk_test"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Add NDK configuration
        ndk {
            // Specify ABIs if needed
            abiFilters += listOf("arm64-v8a", "armeabi-v7a", "x86", "x86_64")
        }

        externalNativeBuild {
            cmake {
                // 명시적으로 C++ STL 라이브러리를 shared 방식으로 설정
                arguments += listOf(
                    "-DANDROID_STL=c++_shared",   // 공유 방식 STL 사용
                    "-DANDROID_CPP_FEATURES=rtti exceptions"  // RTTI와 예외처리 활성화
                )

                cppFlags += listOf("-std=c++17", "-frtti", "-fexceptions")  // C++ 컴파일러 플래그 설정
                cFlags += "-std=c11"   // C 컴파일러 플래그 설정 (C11 사용)
            }
        }
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

    kotlinOptions {
        jvmTarget = "11"
    }

    externalNativeBuild {
        cmake.apply {
            path = file("src/main/cpp/CMakeLists.txt")  //CMakeLists.txt의 위치 지정
            version = "3.22.1"  // 사용할 CMake 버전
        }
    }

    buildFeatures {
        viewBinding = true
    }

}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

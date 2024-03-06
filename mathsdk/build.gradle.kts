import net.thebugmc.gradle.sonatypepublisher.PublishingType.AUTOMATIC
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties


plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
    id("net.thebugmc.gradle.sonatype-central-portal-publisher") version "1.2.2"
}

description = "Math library"
group = "io.github.propogand"
version = "0.0.4"

android {
    namespace = "com.github.propogand.mathsdk"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

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
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

//signing {
//    useInMemoryPgpKeys(
//        gradleLocalProperties(rootDir).getProperty("signing.keyId"),
//        gradleLocalProperties(rootDir).getProperty("signing.password"),
//        gradleLocalProperties(rootDir).getProperty("signing.secretKeyRingFile")
//    )
//}

centralPortal { // all settings here are optional, but you better setup `pom`

    username = gradleLocalProperties(rootDir).getProperty("centralPortal.username")
    password = gradleLocalProperties(rootDir).getProperty("centralPortal.password")

    publishingType = AUTOMATIC
    name = "mathsdk"

    pom {
        packaging = "aar"
        url = "https://github.com/Propogand/MathSdk"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "Propogand"
                name = "Vladislav"
                email = "reshgand@yandex.ru"
            }
        }
        scm {
            connection = "scm:git:git://github.com/Propogand/MathSdk.git"
            developerConnection = "scm:git:ssh://github.com/Propogand/MathSdk.git"
            url = "https://github.com/Propogand/MathSdk"
        }
    }
    versionMapping { /*...*/ }
}

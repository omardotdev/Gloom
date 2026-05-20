import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryExtension
import org.gradle.kotlin.dsl.configure

plugins {
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.multiplatform.library)
    alias(libs.plugins.aboutlibraries)
}

kotlin {
    applyDefaultHierarchyTemplate()

    extensions.configure<KotlinMultiplatformAndroidLibraryExtension> {
        namespace = "dev.materii.gloom.ui"

        compileSdk = 36
        minSdk = 23
    }

    jvm("desktop")

    jvmToolchain(21)

    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":api"))
                implementation(project(":shared"))

                implementation(libs.bundles.kotlinx)
                implementation(libs.bundles.voyager)

                api("org.jetbrains.compose.material3:material3:1.9.0")
                implementation(libs.material.icons.extended)
                implementation(libs.runtime)

                implementation(libs.coil.compose)
                implementation(libs.coil.network.ktor3)
                implementation(libs.compose.pdf)
                implementation(libs.compose.webview.multiplatform)
                implementation(libs.highlights)
                implementation(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.ktor.client.core)
                implementation(libs.multiplatform.paging)
                implementation(libs.multiplatform.paging.compose)
                implementation(libs.zoomable)

                // Needed for shared module resources to work
                implementation(libs.moko.resources.compose)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.androidx.core.ktx)
                implementation(libs.coil.gif)
                implementation(libs.koin.android)
            }
        }
    }
}
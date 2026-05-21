import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryExtension
import org.gradle.kotlin.dsl.configure

plugins {
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.multiplatform.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.aboutlibraries)
    alias(libs.plugins.moko.resources)
}

kotlin {
    extensions.configure<KotlinMultiplatformAndroidLibraryExtension> {
        namespace = "dev.materii.gloom.shared"

        compileSdk = libs.versions.compileSdk.get().toInt()
        minSdk = libs.versions.minSdk.get().toInt()
    }

    jvm("desktop")

    jvmToolchain(21)

    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.runtime)
                implementation(libs.bundles.kotlinx)

                implementation(libs.apollo.runtime)
                implementation(libs.apollo.normalized.cache)
                implementation(libs.koin.core)
                implementation(libs.moko.resources.compose)
                implementation(libs.multiplatform.settings)

                api(libs.aboutlibraries.core)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.androidx.browser)
                implementation(libs.androidx.core.ktx)
            }
        }
    }
}

multiplatformResources {
    resourcesPackage = "dev.materii.gloom"
    resourcesClassName = "Res"
}
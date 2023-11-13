plugins {
    kotlin("android") version libs.versions.kotlin.get() apply false
    id("com.android.application") version libs.versions.agp.get() apply false
    id("com.android.library") version libs.versions.agp.get() apply false
    id("com.google.devtools.ksp") version libs.versions.ksp.get() apply false
}

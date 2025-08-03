import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

val mockKVersion = "1.13.8"

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("org.jetbrains.kotlin.plugin.allopen")
    id("org.jetbrains.kotlin.plugin.noarg")
    id("org.jetbrains.kotlin.plugin.jpa")
    kotlin("jvm")
    kotlin("plugin.spring")

    kotlin("kapt")

    // KLint
    id("org.jlleitschuh.gradle.ktlint")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

allprojects {
    group = "personal.brian"
    version = "1.0.0"

    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")

    apply(plugin = "kotlin-kapt")

    // KLint
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    dependencies {
        // Kotlin Standard Library
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")

        // Jackson
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("com.fasterxml.jackson.module:jackson-module-afterburner")

        // Spring
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.springframework.boot:spring-boot-starter-aop")
        implementation("org.springframework.boot:spring-boot-starter-logging")
        implementation("org.springframework:spring-tx")
        kapt("org.springframework.boot:spring-boot-configuration-processor")

        // Validation
        implementation("jakarta.validation:jakarta.validation-api")

        // Test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("io.mockk:mockk:$mockKVersion")
    }

    repositories {
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://plugins.gradle.org/m2/")
    }

    tasks.withType<KotlinCompile> {
        compilerOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = JvmTarget.JVM_21
        }
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = "21"
        targetCompatibility = "21"
    }

    // apply ktlint for debug
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
    }

    // Jar
    tasks {
        named<Jar>("jar") {
            enabled = true
        }

        named<BootJar>("bootJar") {
            enabled = false
        }
    }

    // Ignore ktlint
    gradle.taskGraph.whenReady {
        val isBuildTriggered = allTasks.any { it.path.endsWith(":build") }

        if (isBuildTriggered) {
            tasks.matching {
                it.name in
                        listOf(
                            "ktlintCheck",
                            "ktlintFormat",
                            "runKtlintCheckOverMainSourceSet",
                            "runKtlintCheckOverTestSourceSet",
                            "runKtlintCheckOverKotlinScripts",
                            "runKtlintFormatOverMainSourceSet",
                            "runKtlintFormatOverTestSourceSet",
                            "runKtlintFormatOverKotlinScripts",
                            "loadKtlintReporters",
                        )
            }.configureEach {
                enabled = false
            }
        }
    }
}

val nonDependencyProjects =
    File("commons").listFiles()?.let { files ->
        files.filter { it.isDirectory }
            .map { it.name }
    } ?: listOf()

configure(subprojects.filter { it.name !in nonDependencyProjects }) {
    dependencies {
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

// Jar
tasks {
    named<Jar>("jar") {
        enabled = true
    }

    named<BootJar>("bootJar") {
        enabled = false
    }
}
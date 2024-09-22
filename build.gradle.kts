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
    group = "team.brian"
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

        // Jackson
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("com.fasterxml.jackson.module:jackson-module-afterburner")

        implementation("io.github.oshai:kotlin-logging-jvm:5.1.0")
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
}

val nonDependencyProjects =
    listOf("lecture")
        .map { File(it) }
        .mapNotNull { it.listFiles() }
        .flatMap { files ->
            files.filter { it.isDirectory }
                .map { it.name }
        }
        .plus(arrayOf("commons", "lecture"))

// lectures 모듈을 제외하고 Spring 기본 의존성을 추가해준다 -> lecture 모듈은 Spring Webflux를 이용
configure(subprojects.filter { it.name !in nonDependencyProjects }) {
    dependencies {
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

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

val lectureDependencies =
    File("lecture")
        .listFiles()
        ?.filter { it.isDirectory }
        ?.map { it.name }
        ?: emptyList()

configure(subprojects.filter { it.name in lectureDependencies }) {
    dependencies {
        // Spring Webflux
        implementation("org.springframework.boot:spring-boot-starter-webflux")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
        implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")

        // R2DBC
        implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")

        // Actuator
        implementation("org.springframework.boot:spring-boot-starter-actuator")

        // Thymleaf
        implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

        // Kafka
        implementation("org.springframework.cloud:spring-cloud-stream")
        implementation("org.springframework.cloud:spring-cloud-stream-binder-kafka-reactive:4.1.3")

        // Runtime dependencies
        runtimeOnly("com.mysql:mysql-connector-j")
        runtimeOnly("io.asyncer:r2dbc-mysql")

        // Test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("io.mockk:mockk:1.13.2")
        testImplementation("io.projectreactor:reactor-test")
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

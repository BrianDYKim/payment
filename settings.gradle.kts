rootProject.name = "payment"

pluginManagement {
    val kotlinVersion = "2.0.0"
    val springBootVersion = "3.5.4"
    val springDependencyManagementVersion = "1.1.7"

    val kLintVersion = "12.0.2"

    plugins {
        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version springDependencyManagementVersion
        id("org.jetbrains.kotlin.plugin.allopen") version kotlinVersion
        id("org.jetbrains.kotlin.plugin.noarg") version kotlinVersion
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion

        kotlin("kapt") version kotlinVersion

        // Kotlin Formatting
        id("org.jlleitschuh.gradle.ktlint") version kLintVersion
    }
}

// 다중 디렉토리 탐색이 가능하게 설정
fun File.registerGradleModulesRecursively(parentPath: String = this.name) {
    this.listFiles()
        ?.filter { it.isDirectory && !it.name.startsWith(".") }
        ?.forEach { dir ->
            val modulePath = "$parentPath:${dir.name}"

            // 해당 디렉토리가 유효한 Gradle 모듈인지 확인
            val hasGradleFile = dir.resolve("build.gradle.kts").exists() || dir.resolve("build.gradle").exists()

            if (hasGradleFile) {
                include(modulePath)
            }

            // 하위 디렉토리도 계속 탐색 (모듈 여부는 내부에서 판단)
            dir.registerGradleModulesRecursively(modulePath)
        }
}

// 모듈 등록
listOf("commons", "ledger", "wallet")
    .map { File(it) }
    .map { it.registerGradleModulesRecursively() }

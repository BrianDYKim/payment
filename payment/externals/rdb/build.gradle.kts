val querydslVersion = "5.1.0"

dependencies {
    implementation(project(":commons:jpa-extension"))
    implementation(project(":commons:spring-extension"))

    implementation(project(":payment:domain"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Query DSL
    implementation("com.querydsl:querydsl-jpa:$querydslVersion:jakarta")
    kapt("com.querydsl:querydsl-apt:$querydslVersion:jakarta")
    kapt("jakarta.annotation:jakarta.annotation-api")
    kapt("jakarta.persistence:jakarta.persistence-api")

    // Connector
    runtimeOnly("com.mysql:mysql-connector-j")
}

tasks {
    named<Delete>("clean") {
        delete("src/main/generated")
    }
}

// Add the duplicatesStrategy setting to your tasks that may have duplicates
tasks.withType<Copy> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

// If you are creating a JAR file, you may also want to configure the jar task
tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

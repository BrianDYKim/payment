dependencies {
    implementation(project(":commons:extension"))

    implementation(project(":payment:domain"))

    implementation(project(":payment:externals:rdb"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}

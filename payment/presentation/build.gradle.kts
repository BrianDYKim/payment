dependencies {
    api(project(":payment:application"))
    api(project(":payment:externals:psp-toss"))

    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
}

dependencies {
    api(project(":commons:response"))
    api(project(":commons:spring-extension"))
    api(project(":commons:exception"))

    api(project(":payment:application"))

    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
}

val swaggerVersion = "2.0.4"

dependencies {
    api(project(":commons:extension"))
    // Swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$swaggerVersion")
}

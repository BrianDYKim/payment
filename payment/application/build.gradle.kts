dependencies {
    implementation(project(":commons:spring-extension"))
    implementation(project(":commons:extension"))

    implementation(project(":payment:domain"))

    implementation(project(":payment:infrastructure"))

    implementation(project(":payment:externals:psp-toss"))
    implementation(project(":payment:externals:rdb"))
}

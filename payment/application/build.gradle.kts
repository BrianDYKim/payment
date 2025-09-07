dependencies {
    api(project(":commons:spring-extension"))
    api(project(":commons:extension"))

    api(project(":payment:domain"))

    api(project(":payment:infrastructure"))

    api(project(":payment:externals:psp-toss"))
    api(project(":payment:externals:rdb"))
}

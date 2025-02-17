import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks

bootJar.enabled = true

dependencies {
    implementation(project(":chat-adapter"))
    implementation(project(":chat-application"))
    implementation(project(":chat-domain"))
    implementation(project(":chat-infrastructure"))

    // eureka client
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:4.2.0")
    // spring config
    implementation("org.springframework.cloud:spring-cloud-starter-config:4.2.0")
    // bootstrap
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap:4.2.0")
}
import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks

bootJar.enabled = true

dependencies {
    implementation(project(":common"))
    implementation(project(":user-application"))
    // spring security
    implementation("org.springframework.boot:spring-boot-starter-security")
    // eureka client
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:4.2.0")

    // spring config
    implementation("org.springframework.cloud:spring-cloud-starter-config:4.2.0")
    // bootstrap
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap:4.2.0")
}
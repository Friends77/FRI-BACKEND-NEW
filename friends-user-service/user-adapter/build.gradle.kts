import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks

bootJar.enabled = true

dependencies {
    implementation(project(":common"))
    // spring security
    implementation("org.springframework.boot:spring-boot-starter-security")
}
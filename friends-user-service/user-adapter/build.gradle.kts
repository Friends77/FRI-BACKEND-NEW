import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks

bootJar.enabled = true

dependencies {
    implementation(project(":common"))
    implementation(project(":user-application"))
    implementation(project(":user-domain"))
    // spring security
    implementation("org.springframework.boot:spring-boot-starter-security")
}
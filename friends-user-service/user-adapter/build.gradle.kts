import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    implementation(project(":user-application"))
    implementation(project(":auth-application"))
    // spring security
    implementation("org.springframework.boot:spring-boot-starter-security")
}
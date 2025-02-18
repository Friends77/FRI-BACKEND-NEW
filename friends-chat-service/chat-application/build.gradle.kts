import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    implementation(project(":chat-domain"))

    // feign client
    api("org.springframework.cloud:spring-cloud-starter-openfeign:4.2.0")
}
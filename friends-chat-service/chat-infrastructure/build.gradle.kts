import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    implementation(project(":chat-domain"))

    // postgresql
    runtimeOnly("org.postgresql:postgresql")

    // kafka
    implementation("org.springframework.kafka:spring-kafka")
}
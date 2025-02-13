import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    implementation(project(":user-domain"))

    // postgresql
    runtimeOnly("org.postgresql:postgresql")

    // redis
    implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")
}
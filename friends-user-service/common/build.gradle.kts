import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    // JWT
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    implementation("io.jsonwebtoken:jjwt-impl:0.12.6")
    implementation("io.jsonwebtoken:jjwt-jackson:0.12.6")
    // mail
    implementation("org.springframework.boot:spring-boot-starter-mail")

    // ulid creator
    implementation("com.github.f4b6a3:ulid-creator:5.0.0")

    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}
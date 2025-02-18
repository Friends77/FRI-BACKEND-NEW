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
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    // mail
    implementation("org.springframework.boot:spring-boot-starter-mail")

    // JWT
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    implementation("io.jsonwebtoken:jjwt-impl:0.12.6")
    implementation("io.jsonwebtoken:jjwt-jackson:0.12.6")

    // thyemleaf
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    // spring security
    implementation("org.springframework.boot:spring-boot-starter-security")

    // web client
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    // kafka
    implementation("org.springframework.kafka:spring-kafka")
}
plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // eureka client
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:4.2.0")
    // spring config
    implementation("org.springframework.cloud:spring-cloud-starter-config:4.2.0")
    // bootstrap
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap:4.2.0")

    // feign client
    api("org.springframework.cloud:spring-cloud-starter-openfeign:4.2.0")

    // kafka
    implementation("org.springframework.kafka:spring-kafka")

    // websocket
    implementation("org.springframework.boot:spring-boot-starter-websocket")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

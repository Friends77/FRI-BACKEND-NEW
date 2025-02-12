import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    implementation(project(":common"))
    // ulid creator
    implementation("com.github.f4b6a3:ulid-creator:5.0.0")
}
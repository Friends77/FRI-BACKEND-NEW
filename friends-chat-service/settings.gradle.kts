plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "friends-chat-service"
include("chat-boot")
include("chat-adapter")
include("chat-application")
include("chat-domain")
include("chat-infrastructure")

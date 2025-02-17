plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "friends-message-service"
include("message-boot")
include("message-adapter")
include("message-domain")
include("message-application")
include("message-infrastructure")

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "friends-user-service"

include("user-domain")
include("user-application")
include("user-adapter")
include("user-infrastructure")
include("auth-application")
include("user-boot")

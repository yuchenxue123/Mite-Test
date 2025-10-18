plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
}

gradlePlugin {
    plugins {
        register("package-plugin") {
            id = "package-plugin"
            implementationClass = "PackagePlugin"
        }
    }

}
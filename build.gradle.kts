import Information.access_widener
import Information.maven_group
import Information.maven_name
import Information.mod_description
import Information.mod_id
import Information.mod_name
import Information.mod_version
import Versions.loader_version
import Versions.minecraft_version
import java.util.*

plugins {
    `kotlin-jvm`
    `fml-loom`
    `package-plugin`
    id("maven-publish")
}

version = mod_version

base {
    archivesName = mod_name.lowercase()
}

repositories {
    jetpack()
    mite()
}

loom {
    accessWidenerPath = file("src/main/resources/$access_widener")
    mergedMinecraftJar()
    fml = File("loader/loader-$loader_version.jar")

    mods {
        create(mod_name) {
            sourceSet(sourceSets.main.get())
        }
    }
}

dependencies {
    minecraft("com.mojang:minecraft:${minecraft_version}")
    mappings(loom.fmlMCPMappings())
    implementation(files(loom.fml.toPath()))

    implementation(Libraries.kotlin_language)
    packageImplementation(Libraries.joml)
}

val properties = mapOf(
    "id" to mod_id,
    "name" to mod_name,
    "version" to mod_version,
    "description" to mod_description,
    "widener" to access_widener
)

tasks.processResources {
    inputs.property("version", mod_version)

    filesMatching("fml.mod.json") {
        expand(properties)
    }
}

tasks.jar {
    from({
        configurations.packageImplementation.get().map {
            if (it.isDirectory) it else zipTree(it)
        }
    }) {
        exclude("META-INF/maven/**")
        exclude("META-INF/versions/**")

        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    from(arrayOf("LICENSE.txt", "NOTICE.txt")) {
        into("META-INF/")
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release.set(17)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmToolchain(17)
    }
}

// publish to gitlab, you can delete this.

val config = Properties().apply {
    val file = file("config.properties")
    if (file.exists()) {
        file.inputStream().use { load(it) }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])

            groupId = maven_group
            artifactId = maven_name
            version = mod_version
        }
    }

    repositories {
        maven {
            url = uri("https://gitlab.com/api/v4/projects/74192719/packages/maven")

            val private_token: String = config.getProperty("private_token") ?: ""

            credentials(HttpHeaderCredentials::class) {
                name = "Private-Token"
                value = private_token
            }

            authentication {
                create<HttpHeaderAuthentication>("header")
            }
        }
    }
}


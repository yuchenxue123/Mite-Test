import org.gradle.api.artifacts.dsl.RepositoryHandler
import java.net.URI

fun RepositoryHandler.jetpack() = maven {
    name = "Jetpack"
    url = URI.create("https://jitpack.io")
}

fun RepositoryHandler.mite() = maven {
    name = "Mite"
    url = URI.create("https://gitlab.com/api/v4/projects/74192719/packages/maven")
}
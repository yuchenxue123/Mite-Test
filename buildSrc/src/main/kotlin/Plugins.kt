@file:Suppress("ObjectPropertyName")

import Versions.kotlin_version
import Versions.loom_version
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

val PluginDependenciesSpec.`kotlin-jvm`: PluginDependencySpec
    get() = id("org.jetbrains.kotlin.jvm").version(kotlin_version)

val PluginDependenciesSpec.`fml-loom`: PluginDependencySpec
    get() = id("fml-loom").version(loom_version)

val PluginDependenciesSpec.`package-plugin`: PluginDependencySpec
    get() = id("package-plugin")



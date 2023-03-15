plugins {
    id("io.micronaut.application") version "3.7.5"
    id("io.micronaut.test-resources") version "3.7.5"
    id("io.micronaut.aot") version "3.7.5"
}

version = "0.1"
group = "demo"

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor(mn.micronaut.http.validation)
    annotationProcessor(mn.micronaut.data.processor)
    implementation(mn.micronaut.http.client)
    implementation(mn.micronaut.jackson.databind)
    implementation(mn.micronaut.runtime)
    implementation(mn.jakarta.annotation.api)
    implementation(mn.micronaut.validation)
    implementation(mn.micronaut.data.jdbc)
    implementation(mn.micronaut.jdbc.hikari)
    runtimeOnly(mn.logback)
    runtimeOnly(mn.mysql.connector.java)
//    runtimeOnly(mn.postgresql)

    // Dependencies required for Micronaut AOT
//    aotPlugins(mn.logback) {
//        version {
//            require "1.3.0-alpha14"
//        }
//    }
//    aotPlugins(libs.jansi)
}


application {
    mainClass.set("demo.Application")
}
java {
    sourceCompatibility = JavaVersion.toVersion("17")
    targetCompatibility = JavaVersion.toVersion("17")
}

graalvmNative.toolchainDetection.set(false)
micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("demo.*")
    }

    aot {
        cacheEnvironment.set(true)
        optimizeServiceLoading.set(true)
        optimizeClassLoading.set(true)
        convertYamlToJava.set(true)
        precomputeOperations.set(true)
        deduceEnvironment.set(true)
    }
}

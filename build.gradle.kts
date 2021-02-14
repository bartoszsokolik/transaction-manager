import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("groovy")
    kotlin("jvm") version "1.4.21"
    kotlin("plugin.spring") version "1.4.21"
}

group = "pl.sokolik.bartosz"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.vavr:vavr-kotlin:0.10.2")
    implementation("io.github.microutils:kotlin-logging:2.0.4")
    implementation("org.apache.commons:commons-lang3:3.11")
    implementation("org.codehaus.groovy:groovy:3.0.5")
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    implementation("com.sun.xml.bind:jaxb-osgi:2.3.2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.spockframework:spock-core:2.0-M3-groovy-3.0")
    testImplementation("org.spockframework:spock-spring:2.0-M3-groovy-3.0")
    testImplementation("org.testcontainers:mongodb:1.15.2")
    testImplementation("io.rest-assured:rest-assured:4.3.3")
    testImplementation("io.rest-assured:rest-assured-all:4.3.3")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}


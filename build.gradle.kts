plugins {
    id("java")
    id("xyz.jpenilla.run-paper") version "2.2.2"
}

group = "dev.cnbetty.core"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    compileOnly("io.papermc.paper:paper-api:1.20.2-R0.1-SNAPSHOT")
    java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    test {
        useJUnitPlatform()
    }
    runServer {
        minecraftVersion("1.20.4")
    }
}


plugins {
    id("java")
    id("xyz.jpenilla.run-paper") version "2.2.2"
    id("io.papermc.paperweight.userdev") version "1.5.10"
}

group = "dev.cnbetty.core"
version = "1.0"

repositories {
    mavenCentral()
}


dependencies {
    paperweight.paperDevBundle("1.20.4-R0.1-SNAPSHOT")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    compileOnly("io.papermc.paper:paper-api:1.20.2-R0.1-SNAPSHOT")
    java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    test {
        useJUnitPlatform()
    }
    runServer {
        minecraftVersion("1.20.4")
    }
    assemble {
        dependsOn(reobfJar)
    }
}


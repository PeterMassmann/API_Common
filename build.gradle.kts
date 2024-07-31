plugins {
    id("java")
    id("maven-publish")
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
}
val springCloudVersion by extra("2023.0.3")

group = "com.easyspec"
version = "1.0"

repositories {
    mavenCentral()
}

// java version 17
java {
    sourceCompatibility = JavaVersion.VERSION_17
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // spring boot
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

publishing {
    publications {
        register("mavenJava", MavenPublication::class) {
            groupId = "com.easyspec"
            artifactId = "APICommon"
            version = "1.0"

            from(components["java"])
        }
    }
}

tasks.test {
    useJUnitPlatform()
}

// disable bootJar
tasks.named("bootJar") {
    enabled = false
}
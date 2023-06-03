plugins {
    `java-library`
    id("com.palantir.docker") version "0.35.0"
    id("org.springframework.boot") version "2.7.12"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:2.1.7.RELEASE")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:2.1.3.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.1.7.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-security:2.1.7.RELEASE")
    implementation("org.springframework.cloud:spring-cloud-starter-sleuth:2.1.3.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-actuator:2.1.1.RELEASE")
    implementation("org.springframework.boot:spring-boot-devtools:2.1.7.RELEASE")

    implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation("com.sun.xml.bind:jaxb-impl:2.3.1")
    implementation("org.glassfish.jaxb:jaxb-runtime:2.3.1")
    implementation("javax.activation:activation:1.1.1")

    implementation("com.h2database:h2:1.4.199")

    testImplementation("org.springframework.boot:spring-boot-starter-test:2.1.7.RELEASE")
}

group = "currency-conversion"
version = "0.0.2-RELEASE"
description = "currency-conversion"
java.sourceCompatibility = JavaVersion.VERSION_1_8

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}

tasks.jar {
    enabled = false
}

val bootJar = tasks.named("bootJar").get()

tasks.getByName("dockerPrepare", Copy::class) {
    dependsOn(bootJar)
    from(bootJar.outputs.files.singleFile)
    into("build/docker")
    duplicatesStrategy = DuplicatesStrategy.FAIL
}

docker {
    name = "dau47/currency-conversion:$version"
    dependsOn(bootJar)
    buildArgs(mapOf("JAR_FILE" to bootJar.outputs.files.singleFile.name))
    push(true)
    noCache(true)
}

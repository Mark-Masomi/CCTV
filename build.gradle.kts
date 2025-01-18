plugins {
    id("java")
    id ("application")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation (files("libs/opencv-4100.jar"))
    implementation (files("libs/javagps.jar"))
    implementation("io.ipinfo:ipinfo-api:3.0.0")
    implementation("org.apache.httpcomponents.client5:httpclient5:5.4.1")
    implementation ("org.json:json:20210307")
    implementation("com.maxmind.geoip2:geoip2:4.2.1")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("org/example/Main")
}

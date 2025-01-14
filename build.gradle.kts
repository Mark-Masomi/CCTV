plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("org.opencv:opencv:4.5.1")
    implementation ("javax.sound.sampled:javax.sound.sampled-api:1.0.1")

}

tasks.test {
    useJUnitPlatform()
}
plugins {
    kotlin("jvm") version "1.5.0"
    java
}

repositories {
    mavenCentral()
    maven("https://www.jetbrains.com/intellij-repository/releases")
    maven("https://jetbrains.bintray.com/intellij-third-party-dependencies")
}

group = "org.sqdat-capaul-sp21"
version = "1.0-SNAPSHOT"

val exposedVersion: String by project
dependencies {

    //standard libraries/runtimes
    implementation("org.jetbrains.kotlin:kotlin-test-annotations-common")
    implementation("org.jetbrains.kotlin:kotlin-test-common")
    implementation("org.junit.jupiter:junit-jupiter:5.7.0")
    implementation(kotlin("stdlib"))
    implementation(kotlin("script-runtime"))
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")

    //3rd party libraries
    compileOnly("org.xerial:sqlite-jdbc:3.30.1")
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("org.apache.commons:commons-text:1.9")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    testRuntimeOnly("org.xerial:sqlite-jdbc:3.30.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

sourceSets.main {
    java.srcDirs("src/main/java", "src/main/kotlin")
}

plugins {
  kotlin("jvm") version "1.3.71"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
  mavenLocal()
  mavenCentral()
  jcenter()
}

val selenium: String by project
val snakeyaml: String by project
val selenide: String by project
val jackson: String by project
val log4j: String by project
val ashot: String by project
val junit: String by project
val wiremock: String by project
val okhttp: String by project
val exposed: String by project
val mysqldriver: String by project

dependencies {
  implementation(kotlin("stdlib-jdk8"))
  implementation("org.seleniumhq.selenium:selenium-java:$selenium")
  implementation("org.yaml:snakeyaml:$snakeyaml")
  implementation("com.codeborne:selenide:$selenide")
  implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jackson")
  implementation("com.fasterxml.jackson.core:jackson-databind:$jackson")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jackson")
  implementation("org.apache.logging.log4j:log4j-core:$log4j")
  implementation("org.apache.logging.log4j:log4j-api:$log4j")
  implementation("ru.yandex.qatools.ashot:ashot:$ashot")
  implementation("com.squareup.okhttp3:okhttp:$okhttp")
  implementation("com.github.tomakehurst:wiremock-jre8:$wiremock")
  implementation("org.jetbrains.exposed:exposed-core:$exposed")
  implementation("org.jetbrains.exposed:exposed-dao:$exposed")
  implementation("org.jetbrains.exposed:exposed-jdbc:$exposed")
  implementation("org.jetbrains.exposed:exposed-jodatime:$exposed")
  implementation("mysql:mysql-connector-java:$mysqldriver")
  testImplementation("org.junit.jupiter:junit-jupiter:$junit")
}

tasks.named<Test>("test") {
  useJUnitPlatform()
}

open class HeadlessTest : org.gradle.api.tasks.testing.Test()

tasks.register<HeadlessTest>("headlessTest") {
  useJUnitPlatform()
  systemProperties["test.driver.headless"] = "true"
  filter {
    includeTestsMatching("*dTest")
  }
}

tasks {
  compileKotlin {
    kotlinOptions.jvmTarget = "1.8"
  }
  compileTestKotlin {
    kotlinOptions.jvmTarget = "1.8"
  }
}
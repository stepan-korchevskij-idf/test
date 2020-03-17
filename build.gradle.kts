plugins {
  kotlin("jvm") version "1.3.61"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
  mavenLocal()
  mavenCentral()
}

val selenium: String by project
val snakeyaml: String by project
val selenide: String by project
val jackson: String by project
val log4j: String by project
val ashot: String by project
val junit: String by project

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
  testImplementation("org.junit.jupiter:junit-jupiter:$junit")
}

tasks.named<Test>("test") {
  useJUnitPlatform()
}

open class UiTest : org.gradle.api.tasks.testing.Test()

tasks.register<UiTest>("uiTest") {
  useJUnitPlatform()
  systemProperties["test.driver.headless"] = "true"
  filter {
    includeTestsMatching("HeadlessTest")
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
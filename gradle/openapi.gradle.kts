import org.openapitools.generator.gradle.plugin.OpenApiGeneratorPlugin
import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

buildscript {
  repositories {
    maven {
      url = uri("https://plugins.gradle.org/m2/")
    }
  }
  dependencies {
    classpath("org.openapitools:openapi-generator-gradle-plugin:5.1.1")
  }
}

apply<OpenApiGeneratorPlugin>()

val apiWorkdir = "${project.projectDir}/src/main/generated"

val generatePublicApi by tasks.creating(GenerateTask::class) {
  doFirst {
    delete(apiWorkdir)
  }
  outputs.upToDateWhen { false }
  group = "api"
  generatorName.set("kotlin-spring")
  inputSpec.set("$rootDir/specs/public.yaml")
  outputDir.set("$rootDir/build/api")
  apiPackage.set("ae.ekar.public")
  modelPackage.set("ae.ekar.model")
  invokerPackage.set("ae.ekar")
  generateApiTests.set(false)
  ignoreFileOverride.set("$rootDir/.openapi-generator-ignore")
  configOptions.set(
    mapOf(
      "serviceInterface" to "true",
      "packageName" to "ae.ekar.api",
      "enumPropertyNaming" to "UPPERCASE",
      "useTags" to "true"
    )
  )
  doLast {
    copy {
      from("$rootDir/build/api/src/main/kotlin")
      into(apiWorkdir)
    }
  }
}

tasks.named("compileKotlin") {
  dependsOn(generatePublicApi)
}

tasks.named("openApiGenerate") {
  enabled = false
}

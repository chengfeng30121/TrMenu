import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.github.johnrengelman.shadow")
}

dependencies {
    rootProject.subprojects
        .map { it.path }
        .filter { it.startsWith(":project:") }
        .forEach {
            implementation(project(it))
        }
}

tasks {
    withType<ShadowJar> {
        archiveClassifier.set("")
        exclude("META-INF/**")
    }
    build {
        dependsOn(shadowJar)
    }
}

tasks.jar {
    archiveFileName.set(rootProject.name)
}
/*

gradle.buildFinished {
    File(buildDir, "libs").listFiles().first().let {
        it.copyTo(File("F:\\Testing\\Purpur 1.19.2\\plugins\\${it.name}"), true)
    }
}*/

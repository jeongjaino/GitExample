// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.3.1" apply false
}

tasks.register<Delete>("deletePreviousGitHook") {
    val prePush = "${rootProject.rootDir}/.git/hooks/pre-push"
    if (file(prePush).exists()) {
        delete(prePush)
    }
}

tasks.register<Copy>("installGitHook") {
    from("${rootProject.rootDir}/pre-commit")
    into("${rootProject.rootDir}/.git/hooks")
    eachFile {
        fileMode = 777
    }

    tasks.getByPath(":app:preBuild").dependsOn("installGitHook")
}
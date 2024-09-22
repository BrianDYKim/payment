package team.brian.extensions

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExtensionsApplication

fun main(args: Array<String>) {
    runApplication<ExtensionsApplication>(*args)
}

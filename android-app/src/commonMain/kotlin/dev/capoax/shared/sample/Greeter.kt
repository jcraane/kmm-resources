package dev.capoax.shared.sample

import com.example.project.L
import com.example.project.hello

class Greeter {
    fun greet() = L.greetings.hello()
    fun greetGet() = L.greetings.get("hello")
    fun greetIndexed() = L.greetings["hello"]
}

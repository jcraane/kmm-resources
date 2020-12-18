package com.capoax.kmmresources

fun main() {
    val dict: Map<String, Any> = mapOf("a" to "A")
    val dict2: Map<String, Any> = mapOf("a" to mapOf("b" to "a"))

    val newDict = dict as Map<String, Map<String, Any>>
    println(newDict["a"] is Map<String, Any>)

    val newDict2 = dict2 as Map<String, Map<String, Any>>
    println(newDict2["a"] is Map<String, Any>)
}

class MyTestClass {
    fun greet() = "Hello"
}

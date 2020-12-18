package com.capoax.kmmresources.extensions

val Int.indentation: String get() {
    var indentation = ""
    repeat(this) {
        indentation += "  "
    }
    return indentation
}

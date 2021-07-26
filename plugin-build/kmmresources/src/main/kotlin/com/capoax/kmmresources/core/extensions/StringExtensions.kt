package com.capoax.kmmresources.extensions

import java.io.ByteArrayInputStream

/**
 * Returns an InputSTream for this string.
 */
val String.inputStream get() = ByteArrayInputStream(this.toByteArray())

/**
 * @return true if this String contains line breaks, false otherwise.
 */
fun String.isMultiline() = indexOf("\n") != -1

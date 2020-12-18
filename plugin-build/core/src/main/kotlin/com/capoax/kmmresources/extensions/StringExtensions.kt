package com.capoax.kmmresources.extensions

import java.io.ByteArrayInputStream

/**
 * Returns an InputSTream for this string.
 */
val String.inputStream get() = ByteArrayInputStream(this.toByteArray())

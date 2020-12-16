package dev.kmmresources.backport

public inline fun <K, V, M : Map<out K, V>> M.onEachIndexed(action: (index: Int, Map.Entry<K, V>) -> Unit): M {
    return apply { entries.forEachIndexed(action) }
}

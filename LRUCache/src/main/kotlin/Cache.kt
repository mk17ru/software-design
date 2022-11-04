interface Cache<K, V> {

    operator fun get(key: K): V?

    fun put(key: K, value: V)
}
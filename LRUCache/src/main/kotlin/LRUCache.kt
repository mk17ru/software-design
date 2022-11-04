class LRUCache<K, V>(private val maxSize: Int) : Cache<K, V> {

    private val data: HashMap<K, Node<EntryValue<K, V>>>
    private val order: LinkedListNode<EntryValue<K, V>>
    private var size: Int = 0

    init {

        if (maxSize <= 0) {
            throw IllegalArgumentException("MaxSize should be > 0.")
        }

        data = HashMap()
        order = LinkedListNodeImpl()
    }

    override fun get(key: K): V? {

        val value = data[key]

        val ans = if (value != null) {
            order.moveHead(value)

            value.value?.value
        } else {
            null
        }

        checkSize()
        assert(data[key]?.value?.value == ans) {
            "Data should be filled by ans"
        }
        return ans
    }

    private fun checkSize() {
        assert(size <= maxSize) {
            "Size should be smaller than max"
        }
    }

    override fun put(key: K, value: V) {
        assert(maxSize > 0) {
            "Can't put in cache with maxSize <= 0"
        }
        val curValue = data[key]

        if (curValue != null) {
            curValue.value?.value = value
            order.moveHead(curValue)
            data[key] = curValue
        } else {
            val node = Node(EntryValue(key, value), null, null)

            if (size == maxSize) {
                val delTail = order.popTail()
                if (delTail != null) {
                    data.remove(delTail.value?.key)
                }
                order.moveHead(node)
            } else {
                order.moveHead(node)
                size++
            }
            data[key] = node
        }

        checkSize()
        assert(data.containsKey(key) && data[key]?.value?.value == value) {
            "Data should be filled by new answer"
        }
    }


}
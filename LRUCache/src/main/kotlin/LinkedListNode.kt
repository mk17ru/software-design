interface LinkedListNode<V> {

    fun addTail(node: Node<V>)

    fun moveHead(node: Node<V>)

    fun popTail(): Node<V>?

}
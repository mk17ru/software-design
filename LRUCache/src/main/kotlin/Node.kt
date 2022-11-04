class Node<V>(var value: V?, var prevNode: Node<V>?, var nextNode: Node<V>?) {
    constructor(value: V?) : this(value, null, null)
}
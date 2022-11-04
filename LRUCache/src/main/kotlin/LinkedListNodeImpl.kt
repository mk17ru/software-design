class LinkedListNodeImpl<V> : LinkedListNode<V> {

    /*
        Kotlin is null safety, so we shouldn't check that head and tail is null
        they are always not null
     */
    private var head: Node<V> = Node(null)
    private var tail: Node<V> = Node(null)

    init {
        head.nextNode = tail
        tail.prevNode = head
    }

    override fun addTail(node: Node<V>) {
        tail.prevNode?.nextNode = node
        node.prevNode = tail.prevNode

        tail.prevNode = node
        node.nextNode = tail

        assert(tail.prevNode == node) {
            "Node should be on tail!"
        }
    }

    override fun moveHead(node: Node<V>) {
        popNode(node)
        head.nextNode?.prevNode = node
        node.nextNode = head.nextNode

        head.nextNode = node
        node.prevNode = head
        assert(head.nextNode == node) {
            "Node should be on head"
        }
    }

    private fun popNode(node: Node<V>) {
        val prevNode = node.prevNode
        val nextNode = node.nextNode

        if (prevNode != null) {
            prevNode.nextNode = nextNode
        }

        if (nextNode != null) {
            nextNode.prevNode = prevNode
        }

        node.prevNode = null
        node.nextNode = null

        assert(nextNode?.prevNode == prevNode && prevNode?.nextNode == nextNode) {
            "Node should be deleted!"
        }
    }


    override fun popTail(): Node<V>? {
        val answer = tail.prevNode

        if (answer == head) {
            assert(head.nextNode == tail && tail.prevNode == head) {
                "Empty list"
            }
            return null
        }

        val prevNode = answer?.prevNode

        prevNode?.nextNode = tail

        tail.prevNode = prevNode

        assert(tail.prevNode != answer && tail.prevNode?.nextNode == tail) {
            "remove last node"
        }
        return answer
    }


}
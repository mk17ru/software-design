import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class LinkedListNodeTest {

    @Test
    fun addAndRemoveTest() {
        val list = LinkedListNodeImpl<Int>()
        list.addTail(Node(10))
        list.addTail(Node(20))
        list.addTail(Node(30))
        Assertions.assertEquals(30, list.popTail()?.value)
        Assertions.assertEquals(20, list.popTail()?.value)
        Assertions.assertEquals(10, list.popTail()?.value)
        Assertions.assertNull(list.popTail()?.value)
    }


    @Test
    fun moveTest() {
        val list = LinkedListNodeImpl<Int>()
        val node1 = Node(3)
        val node2 = Node(201)
        val node3 = Node(100)

        list.addTail(node1)
        list.addTail(node2)
        list.addTail(node3)
        list.moveHead(node3)
        Assertions.assertEquals(201, list.popTail()?.value)
        Assertions.assertEquals(3, list.popTail()?.value)
        Assertions.assertEquals(100, list.popTail()?.value)
        Assertions.assertNull(list.popTail()?.value)
    }

    @Test
    fun scenarioTest() {
        val list = LinkedListNodeImpl<String>()
        val node1 = Node("node1")
        val node2 = Node("node2")
        val node3 = Node("node3")
        val node4 = Node("node4")


        list.addTail(node1)
        list.addTail(node2)
        list.addTail(node3)
        list.addTail(node4)

        list.moveHead(node2)
        list.moveHead(node3)
        Assertions.assertEquals("node4", list.popTail()?.value)
        Assertions.assertEquals("node1", list.popTail()?.value)

        val nodeLast = Node("last")
        list.addTail(nodeLast)
        list.moveHead(nodeLast)
        Assertions.assertEquals("node2", list.popTail()?.value)
        Assertions.assertEquals("node3", list.popTail()?.value)
        Assertions.assertEquals("last", list.popTail()?.value)
    }

    @Test
    fun removeFromEmpty() {
        val list = LinkedListNodeImpl<Int>()
        list.popTail()
    }

    @Test
    fun doubleMoveHead() {
        val list = LinkedListNodeImpl<String>()
        val node1 = Node("node1")
        val node2 = Node("node2")
        val node3 = Node("node3")


        list.addTail(node1)
        list.addTail(node2)
        list.addTail(node3)

        list.moveHead(node2)
        list.moveHead(node2)
        Assertions.assertEquals("node3", list.popTail()?.value)
        Assertions.assertEquals("node1", list.popTail()?.value)
        Assertions.assertEquals("node2", list.popTail()?.value)
    }

}
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class LRUCacheTest {


    @Test
    fun getTest() {
        val lruCache = LRUCache<String, Int>(5)
        lruCache.put("privet", 1)
        Assertions.assertEquals(1, lruCache["privet"])
        Assertions.assertNull(lruCache["lol"])
        Assertions.assertEquals(1, lruCache["privet"])
    }

    @Test
    fun replaceTest() {
        val lruCache = LRUCache<String, Int>(3)
        lruCache.put("privet", 1)
        lruCache.put("privet", 2)
        Assertions.assertEquals(2, lruCache["privet"])
        lruCache.put("privet", 2)
        Assertions.assertEquals(2, lruCache["privet"])
    }

    @Test
    fun checkOverflowTest() {
        val cache = LRUCache<Int, String>(3)
        cache.put(1, "privet1")
        cache.put(2, "privet2")
        cache.put(3, "privet3")
        cache.put(4, "privet4")

        Assertions.assertNull(cache[1])
        Assertions.assertEquals("privet2", cache[2])
        Assertions.assertEquals("privet3", cache[3])
        Assertions.assertEquals("privet4", cache[4])
    }

    @Test
    fun checkNonElements() {
        val capacity = 25
        val cache = LRUCache<String, Int>(capacity)
        for (i in 0 until capacity) {
            cache.put("Key$i", i)
        }

        for (i in capacity until capacity * 3) {
            Assertions.assertNull(cache.get("Key$i"))
        }
    }

    @Test
    fun checkOrderElements() {
        val capacity = 25
        val cache = LRUCache<String, Int>(capacity)
        for (i in 0 until capacity) {
            cache.put("Key$i", i)
        }

        for (i in capacity until capacity * 2) {
            cache.put("Key$i", i)
            Assertions.assertNull(cache.get("Key${i - capacity}"))
            Assertions.assertEquals(i, cache.get("Key${i}"))
        }
    }

    @Test
    fun checkOrderMultiTest() {
        val cache = LRUCache<Int, String>(3)
        cache.put(1, "privet1")
        cache.put(2, "privet2")
        cache.put(3, "privet3")
        cache.put(4, "privet4")
        cache.put(5, "privet5")

        Assertions.assertNull(cache[1])
        Assertions.assertNull(cache[2])
        Assertions.assertEquals("privet5", cache[5])
        Assertions.assertEquals("privet4", cache[4])
        Assertions.assertEquals("privet3", cache[3])

        cache.put(6, "privet6")
        cache.put(7, "privet7")

        Assertions.assertNull(cache[4])
        Assertions.assertNull(cache[5])
        Assertions.assertEquals("privet3", cache[3])
        Assertions.assertEquals("privet6", cache[6])
        Assertions.assertEquals("privet7", cache[7])
    }

    @Test
    fun wrongCapacityTest() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            LRUCache<Int, Int>(0)
        }
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            LRUCache<Int, Int>(-2)
        }
    }


}
package therockyt.fileshareserver

import java.io.InputStreamReader

class ResourceReader {
    fun readResource(path: String): String? {
        val stream = javaClass.getResourceAsStream(path)
        if (stream != null) {
            val reader = InputStreamReader(stream)
            return reader.readText()
        }
        return null
    }
}
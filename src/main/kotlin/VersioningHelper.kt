import java.io.BufferedReader
import java.io.File

object VersioningHelper {
    const val VERSION_PROPERTIES = "version.properties"
    const val MAJOR = "MAJOR"
    const val MAJOR_DEFAULT = "0"
    const val MINOR = "MINOR"
    const val MINOR_DEFAULT = "0"
    const val PATCH = "PATCH"
    const val PATCH_DEFAULT = "1"
    const val DELIMITER = "="

    private fun getInteger(it: String): Int {
        val int = try {
            it.substringAfter(DELIMITER).toInt()
        } catch (e: Exception) {
            0
        }
        return int
    }

    fun getVersionTripleFromFile(): Triple<Int, Int, Int> {
        val file = File(VERSION_PROPERTIES)
        if (file.exists().not()) {
            file.writeText(
                "$MAJOR$DELIMITER$MAJOR_DEFAULT\n" +
                        "$MINOR$DELIMITER$MINOR_DEFAULT\n" +
                        "$PATCH${DELIMITER}$PATCH_DEFAULT"
            )
        }
        val bufferedReader = file.bufferedReader()
        val allText = bufferedReader.use(BufferedReader::readText)
        var major = 0
        var minor = 0
        var patch = 0
        allText.lines().forEach {
            when {
                it.contains(MAJOR) -> {
                    major = getInteger(it)
                }

                it.contains(MINOR) -> {
                    minor = getInteger(it)
                }

                it.contains(PATCH) -> {
                    patch = getInteger(it)
                }
            }
        }
        return Triple(major, minor, patch)
    }
}

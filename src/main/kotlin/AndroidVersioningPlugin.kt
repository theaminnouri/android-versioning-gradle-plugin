import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.BufferedReader
import java.io.File


class AndroidVersioningPlugin : Plugin<Project> {

    override fun apply(target: Project) {
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

        val versionName = "$major.$minor.$patch"
        val versionCode = major * 10000 + minor * 100 + patch
        setProjectConfig(
            project = target,
            versionCode = versionCode,
            versionName = versionName,
        )
    }

    private fun setProjectConfig(
        project: Project,
        versionName: String,
        versionCode: Int,
    ) {
        project.application().apply {
            defaultConfig {
                this.versionCode = versionCode
                this.versionName = versionName
            }
        }
    }

    private fun Project.application(): ApplicationExtension {
        return extensions.getByType(ApplicationExtension::class.java)
    }

    private fun getInteger(it: String): Int {
        val int = try {
            it.substringAfter(DELIMITER).toInt()
        } catch (e: Exception) {
            0
        }
        return int
    }

    companion object {
        const val VERSION_PROPERTIES = "version.properties"
        const val MAJOR = "MAJOR"
        const val MAJOR_DEFAULT = "0"
        const val MINOR = "MINOR"
        const val MINOR_DEFAULT = "0"
        const val PATCH = "PATCH"
        const val PATCH_DEFAULT = "1"
        const val DELIMITER = "="
    }

}

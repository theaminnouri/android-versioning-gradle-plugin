import org.gradle.api.Plugin
import org.gradle.api.Project


class AndroidVersioningPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        val version = target.extensions.create("versioning", Version::class.java)
        if (version.major.isPresent.not()) {
            val (major, minor, patch) = VersioningHelper.getVersionTripleFromFile()
            version.major.set(major)
            version.minor.set(minor)
            version.patch.set(patch)
        }
        target.tasks.register("printVersion") {
            println("versionName = ${version.versionName}, versionCode ${version.versionCode}")
        }
    }
}

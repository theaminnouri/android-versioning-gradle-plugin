import org.gradle.api.provider.Property

abstract class Version {
    abstract val major: Property<Int>
    abstract val minor: Property<Int>
    abstract val patch: Property<Int>

    val versionName: String
        get() = "${major.get()}.${minor.get()}.${patch.get()}"
    val versionCode
        get() = major.get() * 10000 + minor.get() * 100 + patch.get()
}

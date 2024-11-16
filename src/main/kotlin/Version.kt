import org.gradle.api.provider.Property

interface Version {
    val major: Property<Int>
    val minor: Property<Int>
    val patch: Property<Int>

    val versionName: String
        get() = "${major.get()}.${minor.get()}.${patch.get()}"
    val versionCode
        get() = major.get() * 10000 + minor.get() * 100 + patch.get()
}

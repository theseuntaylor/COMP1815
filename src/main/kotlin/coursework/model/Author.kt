package coursework.model

data class Author(
    val firstName: String,
    val lastName: String


) {
    override fun toString(): String {
        return "$firstName $lastName"
    }
}
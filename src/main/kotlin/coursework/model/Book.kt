package coursework.model

import coursework.model.Author

data class Book(
    var title: String = "John Doe",
    var author: Author,
    var status: String,
    var yearOfPublication: String = "2002",
    var publisher: String = "Penguin Books",
    var subject: String = "IT Subjects"

)
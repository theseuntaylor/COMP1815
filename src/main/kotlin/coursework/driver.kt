package coursework

import coursework.database.BOOKS
import coursework.database.Database
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.asJdbcDriver
import com.zaxxer.hikari.HikariDataSource
import coursework.database.AUTHORS
import coursework.database.PUBLISHERS
import coursework.model.Book
import coursework.model.Publisher

object DDBB {

    private const val path = "src/main/resources/books.sqlite"
    val lecturers = mutableListOf<BOOKS>()
    private val database = Database(getSqlDriver())

    fun getBooks(): List<BOOKS> {
        val sqlQueries = database.cWQueries
        return sqlQueries.allBooks().executeAsList()
    }

    fun addBook(book: Book) {
        val sqlQueries = database.cWQueries
        sqlQueries.insertBook(
            book.title, book.author.toString(), book.status, book.subject, book.yearOfPublication, book.publisher
        )
    }

    fun addPublisher(publisher: Publisher) {
        val sqlQueries = database.cWQueries
        sqlQueries.insertPublisher(
            publisher.name
        )
    }

    private fun getSqlDriver(): SqlDriver {
        val ds = HikariDataSource()
        ds.jdbcUrl = "jdbc:sqlite:$path"
        ds.driverClassName = "org.sqlite.JDBC"
        ds.username = ""
        ds.password = ""
        return ds.asJdbcDriver()
    }

    fun addAuthor(name: String) {
        val sqlQueries = database.cWQueries
        sqlQueries.insertAuthor(name)
    }

    fun getAuthors(): List<AUTHORS> {
        val facultyQueries = database.cWQueries
        return facultyQueries.allAuthors().executeAsList()
    }

    fun getPublishers(): List<PUBLISHERS> {
        val facultyQueries = database.cWQueries
        return facultyQueries.allPublishers().executeAsList()
    }

//    fun deleteBook(bookId: Int) {
//        val sqlQuery = database.cWQueries
//
//        sqlQuery.deleteBook(bookId)
//    }

}

// this is for testing.
fun main() {

//        DDBB.addBook(
//            Book(
//                subject = "Fiction", title = "Vagabonds!", status = "Finished", author = Author(
//                    firstName = "Eloghosa", lastName = "Osunde"
//                ), yearOfPublication = "2022", publisher = "Penguin Random House"
//            )
//        )

    val booksList = DDBB.getBooks()
    for (lecture in booksList) {
        println(lecture)
    }

//    val authorsList = DDBB.getAuthors()
//    for (author in authorsList) {
//        println(author)
//
//    }
}
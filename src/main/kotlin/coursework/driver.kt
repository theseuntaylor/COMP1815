package coursework

import coursework.database.BOOKS
import coursework.database.Database
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.asJdbcDriver
import com.zaxxer.hikari.HikariDataSource
import coursework.database.AUTHORS
import coursework.model.Book


object DDBB {
    val lecturers = mutableListOf<BOOKS>()

    fun getLecturers(path: String = "src/main/resources/lecturer.sqlite"): List<BOOKS> {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        return sqlQueries.allBooks().executeAsList()
    }

    fun addBook(book: Book) {
        val database = Database(getSqlDriver("src/main/resources/lecturer.sqlite"))
        val sqlQueries = database.cWQueries
        sqlQueries.insertBook(
            book.title, book.author.toString(), book.status, book.subject, book.yearOfPublication, book.publisher
        )
    }


    private fun getSqlDriver(path: String = "src/main/resources/lecturer.sqlite"): SqlDriver {
        val ds = HikariDataSource()
        ds.jdbcUrl = "jdbc:sqlite:" + path
        ds.driverClassName = "org.sqlite.JDBC"
        ds.username = ""
        ds.password = ""
        return ds.asJdbcDriver()
    }

    fun addFaculty(name: String) {
        val database = Database(getSqlDriver("src/main/resources/lecturer.sqlite"))
        val sqlQueries = database.cWQueries
        sqlQueries.insertAuthor(name)
    }

    fun getFaculties(path: String = "src/main/resources/lecturer.sqlite"): List<AUTHORS> {
        val database = Database(getSqlDriver(path))
        val facultyQueries = database.cWQueries
        return facultyQueries.allAuthors().executeAsList()
    }

}

// this is for testing.
fun main() {
    val lectures = DDBB.getLecturers()
    for (lecture in lectures) {
        println(lecture)
    }
    println(lectures)

    val faculties = DDBB.getFaculties()
    for (faculty in faculties) {
        println(faculty)

    }
}
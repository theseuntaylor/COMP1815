package coursework

import coursework.database.LECTURER
import coursework.database.Database
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.asJdbcDriver
import com.zaxxer.hikari.HikariDataSource
import coursework.database.FACULTY


object DDBB {
    val lecturers = mutableListOf<LECTURER>()

    fun getLecturers(path: String = "src/main/resources/lecturer.sqlite"): List<LECTURER> {
        val database = Database(getSqlDriver(path))
        val sqlQueries = database.cWQueries
        return sqlQueries.allLecturers().executeAsList()
    }

    fun add_lecturer(name: String,age:Long,status:String,gender:String) {
        val database = Database(getSqlDriver("src/main/resources/lecturer.sqlite"))
        val sqlQueries = database.cWQueries
        sqlQueries.insertLecturer(name,age,status,gender)
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
        sqlQueries.insertFaculty(name)
    }
    fun getFaculties(path: String = "src/main/resources/lecturer.sqlite"): List<FACULTY> {
        val database = Database(getSqlDriver(path))
        val facultyQueries = database.cWQueries
        return facultyQueries.allFaculties().executeAsList()
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
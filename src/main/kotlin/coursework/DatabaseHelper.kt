package coursework

import coursework.model.Author
import coursework.model.Book
import java.io.InputStream

class DatabaseHelper {

     fun filesToRows(inputStream: InputStream): List<Book> {
        val reader = inputStream.bufferedReader()
        val header = reader.readLine()
        return reader.lineSequence().filter { it.isNotEmpty() || it.isNotBlank() }.map {
                val (type, title, status, author, yearOfPublication, publisher) = it.split(",", ignoreCase = false, limit = 7)
                Book(
                    title = title,
                    author = author.handleAuthorName(),
                    status = status,
                    yearOfPublication = yearOfPublication,
                    publisher = publisher,
                    subject = type
                )
            }.toList()
    }

    private fun String.handleAuthorName(): Author {
        return if (this.split(" ").size == 1){
            Author(this.split(" ")[0], "")
        } else {
            Author(this.split(" ")[0], this.split(" ")[1])
        }
    }

    // fun getBooks() = listOfBooks

//    fun saveBook(book: Book): DefaultListModel<Book> {
//
//        listOfBooks.addElement(book)
//
//        return listOfBooks
//    }

}

operator fun <T> List<T>.component6(): T = get(5)
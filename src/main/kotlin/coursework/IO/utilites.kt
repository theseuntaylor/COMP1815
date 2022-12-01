package coursework.IO

object Utilities {

    fun prompt_Int(message:String="DDBB>> "): Int {
        print(message)
        return readLine()!!.toInt() // Kind of assert.
    }

    fun prompt_String(message:String="DDBB>> "): String {
        print(message)
        return readLine()!!
    }

    fun help() {
        val banner = listOf<String>(
            "0 - Exit",
            "1 - Show lecturers [All]",
            "2 - Show (All) faculties",
            "3 - Add Faculty",
            "4 - Add Lecturer",
            "5 - Sort Lecturers [Age]",
            "10 - Help"
        )
        for (i in banner) {
            println(i)
        }
    }

}
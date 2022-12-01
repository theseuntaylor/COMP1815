package coursework.IO

import Bubble
import coursework.DDBB
import coursework.IO.Utilities.help
import coursework.IO.Utilities.prompt_Int
import coursework.IO.Utilities.prompt_String
import coursework.database.FACULTY
import coursework.database.LECTURER


fun LECTURER.toRow(): String = "${this.NAME}\t${this.AGE}\t${this.STATUS}\t${this.GENDER}"

fun FACULTY.toRow(): String = "${this.id}\t${this.NAME}"


fun main() {

    help()
    do {
        var code = prompt_Int()
        try {

            when (code) {
                1 -> DDBB.getLecturers().forEach { lec ->
                    println(lec.toRow())
                }

                2 -> DDBB.getFaculties().forEach { faculty ->
                    println(faculty.toRow())
                }

                3 -> {
                    val name = prompt_String()
                    DDBB.addFaculty(name)
                }

                4 -> {
                    val name = prompt_String("\t Name : ")
                    val age = prompt_Int("\t Age : ").toLong()
                    val status = prompt_String("\t Status : ")
                    val gender = prompt_String("\t Gender : ")
                    DDBB.add_lecturer(name, age, status, gender)
                }

                5 -> {
                    // Mutable
                    val scrambled = DDBB.getLecturers() as ArrayList<LECTURER>
                    val choice = prompt_Int("\tBubble(1),MergeSort(2) : ")
                    var n = 0
                    when (choice) {
                        1 -> {
                            n = Bubble.sort(scrambled)
                            scrambled.forEach { lec ->
                                println(lec.toRow())
                            }
                        }
                        2 -> {
                            val pair = Pair(scrambled,50)
//                            val pair = MergeSort.sort(scrambled)
//                            Bubble2.sort(scrambled.toArray() as Array<out LECTURER>?)
                            n = pair.second
                            pair.first.forEach { lec ->
                                println(lec.toRow())
                            }
                        }

                    }
                    println("Ticks ${n}")
                }

                0 -> println("Bye!")
                10 -> help()
                else -> println("Uncorrect command")
            }
        } catch (e: Exception) {
            println("Uncorrect command")
        }
    } while (code != 0)
    Bubble.sort(DDBB.getLecturers() as ArrayList<LECTURER>)


}



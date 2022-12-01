import coursework.database.LECTURER


object Bubble {

//    fun sort(my_array : ArrayList<LECTURER>, order: (i:LECTURER,j:LECTURER) -> Boolean): Int {
    fun sort(my_array : ArrayList<LECTURER>): Int {

        var n:Int=my_array.size ;
        var ticks = 0 ;
        // Q: n=min p : 0 <= p <= N and p<N-2 -> A[p]>A[p+1]: p
        // quota : n >= 0
        // I : \\forall i : n <= i < size: A[i]<A[i+1]
       do {
            var newn = 0
            // I2: newn > 0 -> \forall i: newn < i <size : A[i-1]<A[i]
            for (i in 1 until n)  {
                ticks += 1
                if (my_array[i-1].AGE > my_array[i].AGE) {
//                if (order(my_array[i-1],my_array[i])) {
                    val tmp = my_array[i-1]
                    my_array[i-1]=my_array[i]
                    my_array[i] = tmp
                    newn = i
                }
            }
            n = newn
        }  while (n>1)
//        for (i in my_array)     println(i.AGE)
//        println("----")

        return ticks
    }


}
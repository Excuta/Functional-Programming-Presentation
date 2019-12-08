fun main(args: Array<String>) {

    val pure = {
        fun x(): Int {
            return 5
        }

        var foo: Int = 0
        fun add(x: Int, y: Int): Int {
            foo = x + y
            return foo
        }

        fun addPure(x: Int, y: Int): Int {
            return x + y
        }

        fun saveUser(user: User) {
            val dao = UserDao()
            dao.save(user)
        }

        fun getUser(id: Int, userDao: UserDao): User {
            return userDao.get(id)
        }
    }()

    val higherOrder = {
        // declare function as a value
        val filter = { x: Int -> x != 0 }

        //funtion that takes a list of numbers
        // and filers them using a function parameter (predicate)
        fun filterNumber(numbers: List<Int>, predicate: (x: Int) -> Boolean): List<Int> {
            return numbers.filter(predicate)
        }

        //this function creates a numberFilter Function using the passed Function
        fun createNumberFilter(predicate: (x: Int) -> Boolean): (List<Int>) -> List<Int> {
            return { numbers: List<Int> -> numbers.filter(predicate) }
        }

        val filterZeros = createNumberFilter(filter)
        val numbers = listOf(2, 0, 5, 6, 8, 0)
        println(filterZeros(numbers)) //prints 2,5,6,8

    }()

    val currying = {
        // Not composable
        fun addNotComposable(x: Int, y: Int): Int {
            return x + y
        }

        fun addComposable(x: Int): (Int) -> Int {
            return { y: Int -> x + y }
        }

        val add5 = addComposable(5)
        println(add5(10)) // prints 15

        // another way
        val add: ((Int) -> (Int) -> Int) = { x: Int -> { y: Int -> x + y } }
        val anotherAdd5 = add(5)
        println(anotherAdd5(10)) // prints 15

    }()
}

class UserDao {
    fun get(id: Int): User {
        return User(id)
    }

    fun save(user: User) {

    }
}

class User(val id: Int) {
}

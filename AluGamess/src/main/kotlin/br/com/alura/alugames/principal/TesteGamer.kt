import br.com.alura.alugames.modelo.Gamer

fun main() {
    val gamer1 = Gamer(nome = "   ", email = "jacque@email.com")
    println(gamer1)

    val gamer2 = Gamer(
        nome = "Jeni",
        email = "jeni@gmail.com",
        dataNascimento = "19/19/1992",
        usuario = "jeniblo"
    )


    println(gamer2)

    gamer1.let {
        it.dataNascimento = "18/09/2000"
        it.usuario = "jacqueskywalker"

    }.also {
        println(gamer1.idInterno)
    }

    println(gamer1)
    gamer1.usuario = "jacque"
    println(gamer1)
}
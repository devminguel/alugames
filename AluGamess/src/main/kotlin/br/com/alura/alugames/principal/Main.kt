package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.modelo.Jogo
import br.com.alura.alugames.servico.ConsumoApi
import transformarEmIdade
import java.util.Scanner


fun main() {

    val leitura = Scanner(System.`in`)
    val gamer = Gamer.criarGamer(leitura)
    println("Cadastro concluido com sucesso. Dados do gamer:")
    println(gamer)
    println("Idade do gamer:"+gamer.dataNascimento?.transformarEmIdade())

    do {
        println(" Digite o codigo do Game para buscar : ")
        val busca = leitura.nextLine()

        val buscaApi = ConsumoApi()
        val informacaoJogo = buscaApi.buscaJogo(busca)

        var meuJogo: Jogo? = null

        val resultado = runCatching {
            meuJogo = Jogo(
                informacaoJogo.info.title,
                informacaoJogo.info.thumb
            )
        }

        resultado.onFailure {
            println("br.com.alura.alugames.modelo.Jogo inexistente. Tente outro id.")
        }



        resultado.onSuccess {
            println("Deseja inserir uma descricacao personalizada ? S/N")
            val opcao = leitura.nextLine()
            if (opcao.equals("s", true)) {
                println("Insira a descricao personalizada para o jogo:")
                val descricaoPersonalizada = leitura.nextLine()
                meuJogo?.descricao = descricaoPersonalizada
            } else {
                meuJogo?.descricao = meuJogo?.titulo
            }
            gamer.jogosBuscados.add(meuJogo)
        }


           println("Deseja buscar um novo Jogo ? S/N")
            val resposta = leitura.nextLine()
    }while(resposta.equals("s", ignoreCase = true))

    println("Jogos Buscados")
    println(gamer.jogosBuscados)

    println("jogos ordenados por titulo:")
    gamer.jogosBuscados.sortBy {
        it?.titulo
    }

    gamer.jogosBuscados.forEach {
        println("Titulo:"+it?.titulo)
    }

    val jogosFiltrados = gamer.jogosBuscados.filter{
        it?.titulo?.contains("batman", true) ?:false
    }
    println("\n Jogos Filtrados")
    println(jogosFiltrados)

    println("Deseja excluir algum jogo da lista original? S/N ")
    val opcao = leitura.nextLine()
    if (opcao.equals("s", ignoreCase = true)) {
        println(gamer.jogosBuscados)
        println("\n Informe a posicao do jogo que deseja excluir")
        val posicao = leitura.nextInt()
        gamer.jogosBuscados.removeAt(posicao)
    }
    println("\n Lista atualizada:")
    println(gamer.jogosBuscados)

    println("Busca finalizada com sucesso")

}
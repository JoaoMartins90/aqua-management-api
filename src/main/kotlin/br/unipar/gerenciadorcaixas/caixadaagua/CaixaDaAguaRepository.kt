package br.unipar.gerenciadorcaixas.caixadaagua

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface CaixaDaAguaRepository : JpaRepository<CaixaDaAgua, Int> {

    fun findByAtivoTrueOrderByMarcaAscModeloAscCapacidadeAsc(): List<CaixaDaAgua>

    fun existsByMarcaAndModeloAndCapacidade(marca: String, modelo: String, capacidade: Double): Boolean

    fun existsByMarcaAndModeloAndCapacidadeAndIdNot(marca: String, modelo: String, capacidade: Double): Boolean

    @Modifying
    @Query("""
        UPDATE CaixaDaAgua c
        SET c.estoqueAtual = c.estoqueAtual - :quantidade
        WHERE c.id = :id AND c.estoqueAtual >= :quantidade
    """)
    fun baixarEstoque(id: Int, quantidade: Int): Int

    @Modifying
    @Query("""
        UPDATE CaixaDaAgua c
        SET c.estoqueAtual = c.estoqueAtual + :quantidade
        WHERE c.id = :id
    """)
    fun devolverEstoque(id: Int, quantidade: Int): Int
}
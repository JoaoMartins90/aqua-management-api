package br.unipar.gerenciadorcaixas.caixadaagua

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.Instant
import org.hibernate.annotations.CreationTimestamp

@Entity
@Table(name = "caixa_da_agua")
class CaixaDaAgua(

    @Column(nullable = false, length = 60)
    var marca: String,

    @Column(nullable = false, length = 60)
    var modelo: String,

    @Column(nullable = false)
    var capacidade: Int,

    @Column(nullable = false, precision = 6, scale = 2)
    var altura: BigDecimal,

    @Column(nullable = false, precision = 6, scale = 2)
    var largura: BigDecimal,

    @Column(nullable = false, precision = 6, scale = 2)
    var profundidade: BigDecimal,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    var cor: Cor,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    var material: Material,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    var formato: Formato,

    @Column(nullable = false, precision = 10, scale = 2)
    var preco: BigDecimal,

    @Column(name = "estoque_atual", nullable = false)
    var estoqueAtual: Int = 0,

    @Column(nullable = false)
    var ativo: Boolean = true
) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    var criadoEm: Instant? = null
}
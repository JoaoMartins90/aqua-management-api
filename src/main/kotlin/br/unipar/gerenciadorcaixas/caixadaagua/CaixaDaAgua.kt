package br.unipar.gerenciadorcaixas.caixadaagua

import br.unipar.gerenciadorcaixas.caixadaagua.enums.Cor
import br.unipar.gerenciadorcaixas.caixadaagua.enums.Formato
import br.unipar.gerenciadorcaixas.caixadaagua.enums.Material
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.Instant
import org.hibernate.annotations.CreationTimestamp

@Entity
@Table(
    name = "caixa_da_agua",
    uniqueConstraints = [
        UniqueConstraint(name = "caixa_da_agua_modelo_uk", columnNames = ["marca", "modelo", "capacidade"])
    ],
    check = [
        CheckConstraint(name = "caixa_da_agua_capacidade_ck", constraint = "capacidade > 0"),
        CheckConstraint(name = "caixa_da_agua_medidas_ck", constraint = "altura > 0 AND largura > 0 AND profundidade > 0"),
        CheckConstraint(name = "caixa_da_agua_preco_ck", constraint = "preco > 0"),
        CheckConstraint(name = "caixa_da_agua_estoque_ck", constraint = "estoque >= 0")
    ]
)
class CaixaDaAgua(

    @Column(nullable = false, length = 60)
    var marca: String,

    @Column(nullable = false, length = 60)
    var modelo: String,

    @Column(nullable = false)
    var capacidade: Double,

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
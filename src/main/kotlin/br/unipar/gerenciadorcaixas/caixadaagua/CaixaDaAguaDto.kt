package br.unipar.gerenciadorcaixas.caixadaagua

import br.unipar.gerenciadorcaixas.caixadaagua.enums.Cor
import br.unipar.gerenciadorcaixas.caixadaagua.enums.Formato
import br.unipar.gerenciadorcaixas.caixadaagua.enums.Material
import jakarta.validation.constraints.Digits
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.PositiveOrZero
import jakarta.validation.constraints.Size
import java.math.BigDecimal
import java.time.Instant

data class CaixaDaAguaRequest(

    @field:NotBlank @field:Size(max = 60)
    val marca: String,

    @field:NotBlank @field:Size(max = 60)
    val modelo: String,

    @field:Positive
    val capacidade: Double,

    @field:Positive @field:Digits(integer = 4, fraction = 2)
    val altura: BigDecimal,

    @field:Positive @field:Digits(integer = 4, fraction = 2)
    val largura: BigDecimal,

    @field:Positive @field:Digits(integer = 4, fraction = 2)
    val profundidade: BigDecimal,

    val cor: Cor,
    val material: Material,
    val formato: Formato,

    @field:Positive @field:Digits(integer = 8, fraction = 2)
    val preco: BigDecimal,

    @field:PositiveOrZero
    val estoqueAtual: Int = 0
)

data class CaixaDaAguaResponse(
    val id: Int,
    val marca: String,
    val modelo: String,
    val capacidade: Double,
    val altura: BigDecimal,
    val largura: BigDecimal,
    val profundidade: BigDecimal,
    val cor: Cor,
    val material: Material,
    val formato: Formato,
    val preco: BigDecimal,
    val estoqueAtual: Int,
    val ativo: Boolean,
    val criadoEm: Instant
)

fun CaixaDaAguaRequest.toEntity() = CaixaDaAgua(
    marca = marca,
    modelo = modelo,
    capacidade = capacidade,
    altura = altura,
    largura = largura,
    profundidade = profundidade,
    cor = cor,
    material = material,
    formato = formato,
    preco = preco,
    estoqueAtual = estoqueAtual
)

fun CaixaDaAgua.toResponse() = CaixaDaAguaResponse(
    id = id!!,
    marca = marca,
    modelo = modelo,
    capacidade = capacidade,
    altura = altura,
    largura = largura,
    profundidade = profundidade,
    cor = cor,
    material = material,
    formato = formato,
    preco = preco,
    estoqueAtual = estoqueAtual,
    ativo = ativo,
    criadoEm = criadoEm!!
)
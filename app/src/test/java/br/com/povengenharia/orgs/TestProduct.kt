package br.com.povengenharia.orgs

import br.com.povengenharia.orgs.model.Product
import org.junit.Assert
import org.junit.Test
import java.math.BigDecimal


/*Arrange, Act, Assert | Organizar/Preparar, Agir/Executar, Afirmar/Verificar

        Arrange (Organizar/Preparar): Nesta fase, você configura o objeto que está sendo testado.
        Isso inclui a criação de instâncias de objetos, configuração de mocks, definição de inputs
        e quaisquer outras configurações necessárias antes de executar a ação que você deseja testar.
        O objetivo é preparar o ambiente para executar a parte do teste que realmente interessa.

        Act (Agir/Executar): Aqui você executa a ação que está sendo testada.
        Isso geralmente envolve chamar um método ou função com os parâmetros preparados na fase de Arrange.
        Esta etapa é o foco do teste, onde a lógica ou o comportamento que você deseja validar é ativado.

        Assert (Afirmar/Verificar): Nesta última fase, você verifica se o resultado da ação executada na fase de
        Act corresponde às expectativas. Isso geralmente envolve comparar os resultados obtidos com os valores esperados,
        verificando se a função ou método se comportou como esperado. Se os resultados são os esperados, o teste passa;
         se não, o teste falha.
        */
class TestProduct {

    @Test
    fun WhenCreatingAProductWithACertainValueThisValueMustBeValid() {
        val validProduct = Product(
            name = "Banana",
            description = "Banana Prata",
            price = BigDecimal("6.99")
        )
        val priceIsValid = validProduct.priceIsValid
        Assert.assertEquals(true, priceIsValid)
    }

    @Test
    fun ifValueGreaterThanOneHundredThenThrowError() {

        //Arrange
        val invalidProduct = Product(
            name = "Carambola",
            description = "Amarela",
            price = BigDecimal("106.99")
        )

        //Act
        val priceIsValid = invalidProduct.priceIsValid

        //Assert
        Assert.assertEquals(false, priceIsValid)
    }
}
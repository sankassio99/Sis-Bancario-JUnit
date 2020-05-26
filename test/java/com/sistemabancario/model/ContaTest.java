package com.sistemabancario.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de teste base para implementação dos testes
 * unitários para a classe {@link Movimentacao}.
 * Os testes foram gerados pelo IDE apenas como modelo
 * (fiz apenas algumas melhorias de boas práticas 
 * como tirar visibilidade public e definir variáveis como final).
 * 
 * Assim, NENHUM DELES FUNCIONA E O CÓDIGO PRECISA SER ALTERADO
 * de acordo com as regras de negócio dos métodos da classe {@link Movimentacao}.
 * Ao iniciar a alteração de um teste aqui,
 * a primeira coisa a fazer é remover a chamada de fail(),
 * que indica que o teste é apenas um protótipo.
 * 
 * @author Manoel Campos da Silva Filho
 */
class ContaTest {
    //R01
        @Test
        void testSetNumeroValido() {
            final Conta instance = new Conta();
            final String esperado = "12345-6";
            instance.setNumero(esperado);
            final String obtido = instance.getNumero();
            assertEquals(esperado, obtido);
        }


        @Test
        void testNumeroIvalidoNaoArmazena(){
            final Conta instance = new Conta();
            final String  invalido = "123" ;
            assertThrows(IllegalArgumentException.class, () -> instance.setNumero(invalido));

            final String obtido = instance.getNumero();
            assertNotEquals(invalido ,obtido);

        }    

    //R03
        @Test
        void testSetLimiteContaEspecial(){
            final Conta instance = new Conta();
            instance.setEspecial(true);
            final double esperado = 1000;
            instance.setLimite(esperado);
            final double obtido = instance.getLimite();
            assertEquals(esperado, obtido);

        }

        @Test
        void testSetLimiteContaNaoEspecial(){
            final Conta instance = new Conta();
            final double limite = 1000 ;
            assertThrows(IllegalArgumentException.class, () -> instance.setLimite(limite));
        }

    //R04
        @Test
        void TestMovimentacaoNotNull(){
            final Conta instance = new Conta();
            assertNotNull(instance.getMovimentacoes());
        }

}
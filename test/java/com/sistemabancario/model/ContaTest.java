package com.sistemabancario.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

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
            assertEquals(esperado, obtido); //Esse Assert serve para verificar se o esperado é igual ao valor obtido
        }


        @Test
        void testNumeroIvalidoNaoArmazena(){
            final Conta instance = new Conta();
            final String  invalido = "123" ;
            assertThrows(IllegalArgumentException.class, () -> instance.setNumero(invalido));

            final String obtido = instance.getNumero();
            assertNotEquals(invalido ,obtido);//Esse Assert serve para verificar se o esperado NÃO é igual ao valor obtido

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
            //Serve para pegar a exeção emitida pelo método passando um valor invalido.
        }

    //R04
        @Test
        void TestMovimentacaoNotNull(){
            final Conta instance = new Conta();
            assertNotNull(instance.getMovimentacoes());//verifica se o retorno NÃO é null
        }

    //R06
        @Test
        void testSaldoTotalMaisLimite(){
            final Conta instance = new Conta();
            final double limite = 500.0 ;
            final double esperado = limite ;
            instance.setEspecial(true);
            instance.setLimite(limite);

            final double obtido = instance.getSaldoTotal();

            assertEquals(esperado, obtido );

        }    

     //R07
        @Test
        void testeDepositoDinheiro(){
            final double limite = 500.6, deposito = 500.8, esperado =1001.4 ;
            final Conta instance = new Conta();

            instance.setEspecial(true);
            instance.setLimite(limite);
            instance.depositoDinheiro(deposito);

            final double obtido = instance.getSaldoTotal() ;
            assertEquals(esperado, obtido, 0.001 );

        }

    //Testar se no momento do deposito o tipo da movimentacao foi definido como credito
    @Test
    void testeDepositoDinheiroTipoDefinidoComoCredito(){
        final Conta conta = new Conta();
        conta.depositoDinheiro(500.0);

        final List <Movimentacao> movimentacaos = conta.getMovimentacoes();

        final Movimentacao mov = movimentacaos.get(0);

        final char tipoEsperado = 'c';
        final char tipObtido = mov.getTipo() ;
 
        assertEquals(tipoEsperado, tipObtido);
    }     

    //Testar se a movimentação foi cofirmada no momento do deposito 
    @Test
    void testeDepositoDinheiroMovimentacaoConfirmada(){
        final Conta conta = new Conta();
        conta.depositoDinheiro(500.8);

        final List<Movimentacao> movimentacaos = conta.getMovimentacoes() ;
        final Movimentacao mov = movimentacaos.get(0);

        final boolean esperado = true ;
        final boolean obtido = mov.isConfirmada();

        assertEquals(esperado, obtido);
    }

    //Testar se o valor foi atribuido a MOvimentação
    @Test
    void testeDepositoDinheiroValorAtribuidoMovimentacao(){
        final Conta conta = new Conta();
        conta.depositoDinheiro(500.8);

        final List<Movimentacao> movimentacaos = conta.getMovimentacoes() ;
        final Movimentacao mov = movimentacaos.get(0);

        final double esperado = 500.8 ;
        final double obtido = mov.getValor() ;

        assertEquals(esperado, obtido , 0.001);
    }

    //Testar se a movimentaçõa foi adcionada no final da lista de movimentações
    @Test
    void testeDepositoDinheiroMovimentacaoAdicionada(){
        final Conta conta = new Conta();
        final int numeroDeMovimentacoes = 2 ;
        for(int i = 0; i < numeroDeMovimentacoes; i++ ){
            conta.depositoDinheiro(500.8);
        }
        
        final List<Movimentacao> movimentacaos = conta.getMovimentacoes();
        final int obtido = movimentacaos.size();

        final int esperado = numeroDeMovimentacoes ;

        assertEquals(esperado, obtido );

    }

    //Teste passando valor negativo e receber um erro
    @Test
    void testeDepositoDinheiroValorNegativo(){
        final Conta conta = new Conta();
        
        assertThrows(IllegalArgumentException.class,()-> conta.depositoDinheiro(-100));
    }

}
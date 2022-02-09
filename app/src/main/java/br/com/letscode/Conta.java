package br.com.letscode;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Conta {

    private String id_da_conta;
    private String numero_da_conta;
    private String numero_da_agencia;
    private String nome_do_banco;
    private ArrayList<Operacao> operacoes;
    private BigDecimal saldo;

    private void ordenaOperacoes() {
        operacoes.sort((obj1, obj2)->(obj1.getDataHoraOperacao().isAfter(obj2.getDataHoraOperacao()))?1:-1);
    }

    private void calculaSaldo() {
        BigDecimal montanteD = operacoes.stream()
                .filter(operacao -> operacao.getTipo().equals("DEPOSITO"))
                .map(Operacao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal montanteS = operacoes.stream()
                .filter(operacao -> operacao.getTipo().equals("SAQUE"))
                .map(Operacao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        saldo = montanteD.subtract(montanteS);
    }

    public String gerarExtrato() {

        ordenaOperacoes();
        calculaSaldo();

        String strOperacoes = operacoes.stream()
                .map(operacao ->
                "OperacaoBancaria:" + "\n" +
                "   operador: " + operacao.getOperador() + "\n" +
                "   - tipo: " + operacao.getTipo() + "\n" +
                "   - valor: " + operacao.getValor() + "\n" +
                "   - dataHoraOperacao: " + operacao.getDataHoraOperacao() + "\n").collect(Collectors.joining());

        String contaBancaria = "contaBancaria:" + "\n" +
                "   -id: " + id_da_conta + "\n" +
                "   -banco: " + nome_do_banco + "\n" +
                "   -agencia: " + numero_da_agencia + "\n" +
                "   -conta: " + numero_da_conta + "\n" +
                "   -saldo: " + saldo;

        return strOperacoes + contaBancaria;


    }

}

package br.com.letscode;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Operacao {

    private LocalDateTime DataHoraOperacao;
    private String id_da_conta;
    private String operador;
    private String tipo;
    private BigDecimal valor;

    @Override
    public boolean equals(Object obj) {
        Operacao opobj = Operacao.class.cast(obj);

        if (DataHoraOperacao.isEqual(opobj.getDataHoraOperacao())) {
            if(operador.equals(opobj.getOperador())) {
                if(tipo.equals(opobj.getTipo())) {
                    if(valor.compareTo(opobj.getValor()) == 0) {
                        return true;
                    }
                }
            }
        }

        return false;

    }

    @Override
    public int hashCode() {
        int hash = Integer. valueOf(id_da_conta);
        return hash;
    }

}

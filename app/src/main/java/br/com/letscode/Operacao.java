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

}

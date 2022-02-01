package com.pgt360.payment.service.dto.netty;

import com.pgt360.payment.service.dto.flujo.FlujoQueryDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestDto {
    private int idFlujo;
    private int flujo;
    private Double monto;
    private int tipo;
    private int idComercio;
}

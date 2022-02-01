package com.pgt360.payment.controller;

import com.pgt360.payment.service.PagoService;
import com.pgt360.payment.service.dto.netty.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(description = "Endpoint para la gestión de pagos con dispositivo POS")
@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PagoService pagoService;
    public PaymentController(PagoService pagoService){
        this.pagoService = pagoService;
    }
    @ApiOperation(value = "Realizar pago con chip para comercio único", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(path = "/chip/{amount}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto payChipSingleCommerce(@PathVariable(value = "amount", required = true) Double amount){
        return this.pagoService.payChipSingleCommerce(amount);
    }

    @ApiOperation(value = "Realizar pago con chip multicomercio", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(path = "/chip/{amount}/{commerceId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto payChipMultiCommerce(@PathVariable(value = "amount", required = true) Double amount,
                                            @PathVariable(value = "commerceId", required = true) Integer commerceId){
        return this.pagoService.payChipMultiCommerce(amount, commerceId);
    }

    @ApiOperation(value = "Realizar pago sin contacto para comercio único", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(path = "/ctl/{amount}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto payContactlessSingleCommerce(@PathVariable(value = "amount", required = true) Double amount){
        return this.pagoService.payContactlessSingleCommerce(amount);
    }

    @ApiOperation(value = "Realizar pago sin contacto para multicomercio", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(path = "/ctl/{amount}/{commerceId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto payContactlessMultiCommerce(@PathVariable(value = "amount", required = true) Double amount,
                                                   @PathVariable(value = "commerceId", required = true) Integer commerceId){
        return this.pagoService.payContactlessMultiCommerce(amount, commerceId);
    }

    @ApiOperation(value = "Realizar anulación de transacción de comercio único", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(path = "/cancel/{transaction}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto cancelTransactionSingleCommerce(@PathVariable(value = "transaction", required = true) String transaction){
        return this.pagoService.cancelTransactionSingleCommerce(transaction);
    }

    @ApiOperation(value = "Realizar anulación de transacción multicomercio", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(path = "/cancel/{transaction}/{commerceId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto cancelTransactionMultiCommerce(@PathVariable(value = "transaction", required = true) String transaction,
                                                      @PathVariable(value = "commerceId", required = true) Integer commerceId){
        return this.pagoService.cancelTransactionMultiCommerce(transaction, commerceId);
    }

    @ApiOperation(value = "Realizar cierre de comercio único", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(path = "/close/{confirm}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto closeSingleCommerce(@PathVariable(value = "confirm", required = true) Integer confirm){
        return this.pagoService.closeSingleCommerce(confirm);
    }

    @ApiOperation(value = "Realizar cierre de multicomercio", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(path = "/close/{confirm}/{commerceId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto closeMultiCommerce(@PathVariable(value = "confirm", required = true) Integer confirm,
                                          @PathVariable(value = "commerceId", required = true) Integer commerceId){
        return this.pagoService.closeMultiCommerce(confirm, commerceId);
    }

    @ApiOperation(value = "Inicializar dispositivo", authorizations = @Authorization(value = "Bearer"))
    @GetMapping(path = "{commerceId}/init/{confirm}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto initDevice(@PathVariable(value = "commerceId", required = true) int commerceId,
                                  @PathVariable(value = "confirm", required = true) int confirm){
        return this.pagoService.initDevice(commerceId, confirm);
    }
}

package com.pgt360.payment.service.impl;

import com.pgt360.payment.client.handler.ClienteHandler;
import com.pgt360.payment.model.entity.Caja;
import com.pgt360.payment.model.entity.Flujo;
import com.pgt360.payment.service.CajaService;
import com.pgt360.payment.service.ConexionService;
import com.pgt360.payment.service.FlujoService;
import com.pgt360.payment.service.PagoService;
import com.pgt360.payment.service.dto.caja.CajaQueryDto;
import com.pgt360.payment.service.dto.flujo.FlujoAddDto;
import com.pgt360.payment.service.dto.flujo.FlujoQueryDto;
import com.pgt360.payment.service.dto.netty.RequestDto;
import com.pgt360.payment.service.dto.netty.ResponseDto;
import com.pgt360.payment.util.ConstantsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;

@Slf4j
@Service
public class PagoServiceImpl implements PagoService {
    private final FlujoService flujoService;
    private final CajaService cajaService;
    private final ConexionService conexionService;

    public PagoServiceImpl(ConexionService conexionService, CajaService cajaService, FlujoService flujoService){
        this.flujoService = flujoService;
        this.cajaService = cajaService;
        this.conexionService = conexionService;
    }

    @Override
    public ResponseDto payChipSingleCommerce(Double pAmount) {
        return null;
    }

    @Override
    public ResponseDto payChipMultiCommerce(Double pAmount, int pCommerceId) {
        return null;
    }

    @Override
    public ResponseDto payContactlessSingleCommerce(Double pAmount) {
        return null;
    }

    @Override
    public ResponseDto payContactlessMultiCommerce(Double pAmount, int pCommerceId) {
        return null;
    }

    @Override
    public ResponseDto cancelTransactionSingleCommerce(String pTransaction) {
        return null;
    }

    @Override
    public ResponseDto cancelTransactionMultiCommerce(String pTransaction, int pCommerceId) {
        return null;
    }

    @Override
    public ResponseDto closeSingleCommerce(int pConfirm) {
        return null;
    }

    @Override
    public ResponseDto closeMultiCommerce(int pConfirm, int pCommerceId) {
        return null;
    }

    @Override
    public ResponseDto initDevice(int pCommerceId, int pConfirm) {
        log.info("Inicialización de Pos");
        ResponseDto vResponseDto = new ResponseDto();
        if(pConfirm != 1 && (Integer)pConfirm == null) {
            log.error("Inicialización no autorizada");
        } else {
            if((Integer)pCommerceId == null){
                log.error("Id comercio nulo");
            }else{
                Flujo vFlujo = new Flujo();
                CajaQueryDto vCajaQueryDto = cajaService.buscarCajaPorId(1);
                Caja vCaja = new Caja();
                BeanUtils.copyProperties(vCajaQueryDto, vCaja);
                vFlujo.setCaja(vCaja);
                vFlujo.setCodigoFlujo(ConstantsUtil.NUMBER_FLOW_INIT);
                vFlujo.setDescripcion(ConstantsUtil.FLOW_INIT);
                vFlujo.setPaso(1);
                vFlujo.setTamaño(0);

                FlujoAddDto vFlujoAddDto = new FlujoAddDto();
                BeanUtils.copyProperties(vFlujo, vFlujoAddDto);
                vFlujoAddDto.setCaja(vFlujo.getCaja().getId());

                FlujoQueryDto vFlujoQueryDto = this.flujoService.agregarFlujo(vFlujoAddDto);

                RequestDto vRequestDto = new RequestDto();
                vRequestDto.setIdFlujo(vFlujoQueryDto.getId());
                vRequestDto.setFlujo(vFlujoQueryDto.getCodigoFlujo());
                vRequestDto.setIdComercio(1);
                vRequestDto.setTipo(ConstantsUtil.CLIENT_FRONTEND);
                ClienteHandler.sendMessage(vRequestDto);

                vResponseDto.setData(vRequestDto);
                vResponseDto.setEstado(true);
                vResponseDto.setMensaje("Mensaje enviado correctamente!");
            }
        }
        return vResponseDto;
    }
}

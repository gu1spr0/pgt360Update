package com.pgt360.payment.server.handler;

import com.pgt360.payment.service.ConexionService;
import com.pgt360.payment.service.dto.conexion.ConexionAddDto;
import com.pgt360.payment.service.dto.netty.RequestDto;
import com.pgt360.payment.util.ConstantsUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.nio.charset.Charset;
import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {
    public static  ChannelHandlerContext ctx;
    int paso = 1;
    int tam = 0;
    String ack = "06";
    Double montoBOB = 0.00;
    String reciboTRA = "";
    String respHost = "";
    String respHostJson = "";
    boolean pagoChip = false;
    boolean pagoChipMulti = false;
    boolean pagoCtl = false;
    boolean pagoCtlMulti = false;
    boolean anulacionTrans = false;
    boolean anulacionTransMulti = false;
    boolean cierrePos = false;
    boolean cierrePosMulti = false;
    boolean inicializarPos = false;
    boolean isAck1 = false;
    boolean isAck2 = false;
    boolean isAck3 = false;
    boolean isAck4 = false;
    boolean isAck5 = false;

    @Autowired
    ConexionService conexionService;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx){
        Channel incoming = ctx.channel();
        ServerHandler.ctx = ctx;
        log.info("[SERVER]-"+incoming.remoteAddress()+" SE CONECTÓ DISPOSITIVO CON EL ID:"+incoming.id());

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx){
        Channel incoming = ctx.channel();
        ServerHandler.ctx = null;
        log.info("[SERVER] - "+incoming.remoteAddress() + " SE DESCONECTÓ DISPOSITIVO CON EL ID:"+incoming.id()+"\n");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        RequestDto vRequestDto = (RequestDto)msg;
        if(vRequestDto.getTipo()== ConstantsUtil.CLIENT_FRONTEND){

        }else{
            switch (vRequestDto.getIdFlujo()){
                case ConstantsUtil.NUMBER_FLOW_INIT:
                    break;
                case ConstantsUtil.NUMBER_FLOW_CHIP:
                    break;
                case ConstantsUtil.NUMBER_FLOW_CHIP_MULTI:
                    break;
                case ConstantsUtil.NUMBER_FLOW_CTL:
                    break;
                case ConstantsUtil.NUMBER_FLOW_CTL_MULTI:
                    break;
                case ConstantsUtil.NUMBER_FLOW_DELETED:
                    break;
                case ConstantsUtil.NUMBER_FLOW_DELETED_MULTI:
                    break;
                case ConstantsUtil.NUMBER_FLOW_CLOSE:
                    break;
                case ConstantsUtil.NUMBER_FLOW_CLOSE_MULTI:
                    log.info("INICIALIZACION");
                    break;
                default:
                    break;
            }

        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("Canal con id:"+ctx.channel().id()+" activo");
        ConexionAddDto vConexionAddDto = new ConexionAddDto();
        vConexionAddDto.setIdCanal(ctx.channel().id().toString());
        vConexionAddDto.setDispositivo(2);
        Date vDate = new Date();
        vConexionAddDto.setFechaConexion(vDate);
        vConexionAddDto.setHoraConexion(vDate);
        this.conexionService.agregarConexion(vConexionAddDto);
        log.info("Se agregó canal a la base de datos");
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("Canal con id:"+ctx.channel().id()+" inactivo");
        //TODO: Dar de baja en la base de datos.
        log.info("Se dió de baja al dispositivo en la base de datos");
    }

    public static void sendMessage(String msg){
        if (ServerHandler.ctx == null)
            return;
        ByteBuf buf = ctx.alloc().buffer();
        buf.writeCharSequence(msg, Charset.defaultCharset());
        ctx.write(buf);
        ctx.flush();
    }

}

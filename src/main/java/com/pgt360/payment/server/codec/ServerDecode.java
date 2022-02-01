package com.pgt360.payment.server.codec;

import com.pgt360.payment.exception.Message;
import com.pgt360.payment.exception.MessageDescription;
import com.pgt360.payment.service.dto.netty.RequestDto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import org.apache.commons.lang3.SerializationUtils;

import java.nio.charset.Charset;
import java.util.List;

public class ServerDecode extends ReplayingDecoder<RequestDto> {
    private final Charset charset = Charset.forName("UTF-8");

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        RequestDto vRequestDto = null;
        if(in.readableBytes()>0){
            System.out.println("Tamaño:"+in.readableBytes());
            /*byte[] bytes = new byte[in.readableBytes()];
            in.readBytes(bytes);
            vRequestDto = SerializationUtils.deserialize(bytes);
            if(vRequestDto == null){
                Object[] obj = {"Servidor: Request"};
                throw Message.GetBadRequest(MessageDescription.objectNull, obj);
            }
            out.add(vRequestDto);*/
        }

    }
}

package com.pgt360.payment.util;

import com.pgt360.payment.server.handler.ServerHandler;

import java.nio.ByteBuffer;

public class ServerCommunication {
    public static String sendAck(){
        String msg = "06";
        return msg;
    }
    public static String sendConnectionChip(){
        String msg = "02001736303030303030303030313030303030300323";
        SendMessageToPOS(msg);
        return msg;
    }
    public static String sendConnectionCtl(){
        String msg = "02001736303030303030303030313030363030300325";
        SendMessageToPOS(msg);
        return msg;
    }
    public static String sendTransRevNo(){
        String msg = "02002436303030303030303030313030303030311C3438000258580303";
        SendMessageToPOS(msg);
        return msg;
    }
    public static String sendTipoTarjetaCtl(){
        String msg = "02001736303030303030303030313030363030310324";
        SendMessageToPOS(msg);
        return msg;
    }
    public static String sendSolicitudCierre(){
        String msg = "02001736303030303030303030313030313030300322";
        SendMessageToPOS(msg);
        return msg;
    }
    public static String sendSolicitudAnulacion(){
        String msg = "02001736303030303030303030313030353030300326";
        SendMessageToPOS(msg);
        return msg;
    }
    public static String sendConfirmarAnulacion(){
        String msg = "02002436303030303030303030313030353030321C3438000230300305";
        SendMessageToPOS(msg);
        return msg;
    }
    public static String sendSolicitudInicializar(){
        String msg = "02001736303030303030303030313030323030300321";
        SendMessageToPOS(msg);
        System.out.println("Inicializacion completa");
        return msg;
    }
    public static void SendMessageToPOS(String msg){
        byte[] bytes = msg.getBytes();
        String hex = NettyUtil.bytesToHex(bytes);
        ByteBuffer buffer = ByteBuffer.wrap(hex.getBytes());
        System.out.println("Mensaje a enviar al POS: "+buffer);
        ServerHandler.sendMessage(msg);
    }
}

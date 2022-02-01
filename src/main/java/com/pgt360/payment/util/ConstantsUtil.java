package com.pgt360.payment.util;

public class ConstantsUtil {
    //STATES
    public static final String STATE_ACTIVE = "AC";
    public static final String STATE_INACTIVE = "AN";
    public static final String STATE_BLOCKED = "BL";
    public static final String STATE_DELETED = "EL";

    //FLUJO POS LITERAL
    public static final String FLOW_CHIP="CHIP";
    public static final String FLOW_CHIP_MULTI="MULTICHIP";
    public static final String FLOW_CTL="CTL";
    public static final String FLOW_CTL_MULTI="MULTICTL";
    public static final String FLOW_DELETED="DELETED";
    public static final String FLOW_DELETED_MULTI="MULTIDELETED";
    public static final String FLOW_CLOSE="CLOSE";
    public static final String FLOW_CLOSE_MULTI="MULTICLOSE";
    public static final String FLOW_INIT="INIT";

    //FLUJO POS LITERAL
    public static final int NUMBER_FLOW_INIT=1;
    public static final int NUMBER_FLOW_CHIP=2;
    public static final int NUMBER_FLOW_CHIP_MULTI=3;
    public static final int NUMBER_FLOW_CTL=4;
    public static final int NUMBER_FLOW_CTL_MULTI=5;
    public static final int NUMBER_FLOW_DELETED=6;
    public static final int NUMBER_FLOW_DELETED_MULTI=7;
    public static final int NUMBER_FLOW_CLOSE=8;
    public static final int NUMBER_FLOW_CLOSE_MULTI=9;

    //TIPO CLIENTE
    public static final int CLIENT_FRONTEND = 1;
    public static final int CLIENT_POS = 2;
}

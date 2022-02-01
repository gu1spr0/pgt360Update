package com.pgt360.payment.service;

import com.pgt360.payment.service.dto.netty.ResponseDto;

public interface PagoService {
    ResponseDto payChipSingleCommerce(Double pAmount);
    ResponseDto payChipMultiCommerce(Double pAmount, int pCommerceId);
    ResponseDto payContactlessSingleCommerce(Double pAmount);
    ResponseDto payContactlessMultiCommerce(Double pAmount, int pCommerceId);
    ResponseDto cancelTransactionSingleCommerce(String pTransaction);
    ResponseDto cancelTransactionMultiCommerce(String pTransaction, int pCommerceId);
    ResponseDto closeSingleCommerce(int pConfirm);
    ResponseDto closeMultiCommerce(int pConfirm, int pCommerceId);
    ResponseDto initDevice(int pCommerceId, int pConfirm);
}

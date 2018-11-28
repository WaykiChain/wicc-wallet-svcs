package com.waykichain.coin.wicc.vo;

import com.waykichain.coin.ErrorInfo;

import lombok.Data;

@Data
public class WiccCreateContractTxJsonRpcResponse {

	private String id;
	private WiccTxHashResult result;
	private ErrorInfo error;
	
}

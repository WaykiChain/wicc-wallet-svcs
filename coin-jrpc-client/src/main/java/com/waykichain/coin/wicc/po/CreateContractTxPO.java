package com.waykichain.coin.wicc.po;

import lombok.Data;

import java.math.BigInteger;

@Data
public class CreateContractTxPO {
	
	private String userregid;	//send address 的信息
	private String appId;		//合约的ID
	private Long amount;
	private String contract;
	private Long fee;
	private BigInteger height;
	

}

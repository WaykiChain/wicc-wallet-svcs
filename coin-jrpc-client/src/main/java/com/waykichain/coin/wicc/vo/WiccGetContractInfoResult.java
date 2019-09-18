package com.waykichain.coin.wicc.vo;

import lombok.Data;

/**
 * @Author: yanjunlin
 * @CreateDate: 2019/4/15 10:30
 * @Description: $des
 */
@Data
public class WiccGetContractInfoResult {

    private String address;
    private String contract_regid;
    private String memo;
    private String code;
    private String abi;
    private Boolean upgradable;
    private Integer vm_type;
}

package com.waykichain.coin.wicc.po;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: yanjunlin
 * @CreateDate: 2019/4/13 10:04
 * @Description: $des
 */
@Data
public class WiccGenRegisterAccountrawPO {

    /** 激活使用的小费，单位为sawi */
    private BigDecimal fee;
    /** 激活时的区块高度 */
    private Long height;
    /** 激活账户的公钥*/
    private String publickey;
}

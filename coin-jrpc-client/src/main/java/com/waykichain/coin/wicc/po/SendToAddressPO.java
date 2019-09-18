package com.waykichain.coin.wicc.po;

import lombok.Data;

import java.math.BigDecimal;

/** Created at 2018-08-15,
 * @author Joss
 * @since 1.0
 * */

@Data
public class SendToAddressPO {

    public String sendAddress;
    public String recvAddress;
    public BigDecimal amount;


}
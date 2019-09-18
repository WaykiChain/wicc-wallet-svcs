package com.waykichain.coin.wicc.vo;

import lombok.Data;

import java.util.List;

/** Created at 2018-08-15,
 * @author Joss
 * @since 1.0
 * */

@Data
public class WiccListTxResult {

    public List<String> confirmed_tx;
    public List<String> unconfirmed_tx;
}

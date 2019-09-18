package com.waykichain.coin.wicc.vo;

import lombok.Data;

/**
 * @Author: yanjunlin
 * @CreateDate: 2019/8/16 21:26
 * @Description: cdp详情
 */
@Data
public class CdpInfoDetailResult {

       private String cdpid;
       private String regid;
       private Long last_height;
       private String bcoin_symbol = "WICC";
       private Long total_bcoin;
       private String scoin_symbol = "WUSD";
       private Long total_scoin;
       private String collateral_ratio;
}


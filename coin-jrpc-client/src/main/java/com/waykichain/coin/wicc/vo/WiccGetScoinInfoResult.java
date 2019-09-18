package com.waykichain.coin.wicc.vo;

import lombok.Data;

@Data
public class WiccGetScoinInfoResult {

    private Long height;

    private Integer slide_window_block_count;

    private Long global_collateral_ceiling;
    private Long global_staked_bcoins;
    private Long global_owed_scoins;
    private Long force_liquidate_cdp_amount;
    private String global_collateral_ratio;
    private String global_collateral_ratio_floor;
    private Boolean global_collateral_ratio_floor_reached;
    private String force_liquidate_ratio;


}

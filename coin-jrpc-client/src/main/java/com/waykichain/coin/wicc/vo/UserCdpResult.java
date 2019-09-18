package com.waykichain.coin.wicc.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: yanjunlin
 * @CreateDate: 2019/8/17 11:28
 * @Description: user cdp
 */
@Data
public class UserCdpResult {
    private List<CdpInfoDetailResult> user_cdps;
}

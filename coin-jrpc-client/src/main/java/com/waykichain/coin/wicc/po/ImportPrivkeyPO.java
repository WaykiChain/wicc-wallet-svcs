package com.waykichain.coin.wicc.po;

import lombok.Data;

/**
 * @Author: yanjunlin
 * @CreateDate: 2019/4/13 10:25
 * @Description: $des
 */
@Data
public class ImportPrivkeyPO {

    private String privkey;

    private String label;

    private boolean rescan;
}

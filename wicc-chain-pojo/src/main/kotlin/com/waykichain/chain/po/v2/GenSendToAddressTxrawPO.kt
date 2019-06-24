package com.waykichain.chain.po.v2

import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.NotBlank
import java.math.BigDecimal
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/13 15:03
 *
 * @Description:    从源地址账户转账到目的地址账户,手动设置手续费
 *
 */
class GenSendToAddressTxrawPO: SendtoaddressPO() {

    @ApiModelProperty("小费")
    @NotNull(message = "[in param error] fee is null")
    @Min(value = 10000 ,message = "[in param error] Minimum value of fee is 10000sawi")
    var fee: BigDecimal?= null

//    @ApiModelProperty("区块高度")
//    @NotNull(message = "[in param error] height is null")
//    var height: Long?= null

}
package com.waykichain.chain.commons.biz.xservice

import com.waykichain.chain.po.v2.CdpStakeTxPO
import com.waykichain.chain.vo.v2.CdpInfoVO
import com.waykichain.chain.vo.v2.CdpStakeTxVO
import com.waykichain.chain.vo.v2.UserCdpVO
import com.waykichain.commons.base.BizResponse

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/8/16 21:18
 *
 * @Description:    cdp 相关
 *
 */
interface CdpXService {

    fun getCdpInfo(cdpid: String): BizResponse<CdpInfoVO>

    fun getUserCdp(address: String): BizResponse<UserCdpVO>

    fun cdpStake(po: CdpStakeTxPO): BizResponse<CdpStakeTxVO>

}
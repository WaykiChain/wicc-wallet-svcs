package com.waykichain.chain.commons.biz.xservice

import com.waykichain.chain.po.v2.*
import com.waykichain.chain.vo.v2.*
import com.waykichain.commons.base.BizResponse

/**
 *
 * @Author:         yanjunlin
 *
 * @CreateDate:     2019/4/4 15:26
 *
 * @Description:    用户地址相关
 *
 */

interface AccountXservice {


    /**
     * 获取普通账户或者合约账户地址详情[RPC-getaccountinfo]
     */
    fun getAccountInfo(po: QueryAccountDetailPO): BizResponse<AccountDetailVO>

    /**
     * 激活账户 [RPC-registeraccounttx]
     */
    fun registerAccountTx(po: QueryAccountDetailPO): BizResponse<RegisterAccountVO>

    /**
     * 创建新地址[RPC-getnewaddress]
     */
    fun getNewaddress(): BizResponse<NewAddressVO>

    /**
     * 获取账户余额
     */
    fun getAccountBalance(po: QueryAccountDetailPO): BizResponse<AccountBalanceVO>

    /**
     * 检查普通地址或者合约地址是否有效[RPC-validateaddr]
     */
    fun validateAddress(po: ValidateAddressPO): BizResponse<ValidateAddressVO>

    /**
     * 创建离线激活账户的交易内容[RPC-genregisteraccountraw]
     */
//    fun genRegisterAccountraw(po: GenRegisterAccountrawPO): BizResponse<GenRegisterAccountrawVO>

    /**
     * 将私钥（由dumpprivkey导出）导入钱包[RPC-importprivkey]
     */
    fun importPrivkey(po: ImportPrivateKeyPO): BizResponse<ImportPrivateKeyVO>

    /**
     * 根据log查询余额
     */
    fun getBalanceByLog(po: QueryAccountDetailPO): BizResponse<AccountBalanceVO>

    /**
     * 获取所有WICC的数量[RPC-gettotalcoin]
     */
    fun getTotalCoin(): BizResponse<TotalCoinVO>



}
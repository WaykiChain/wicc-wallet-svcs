package com.waykichain.chain.controller

import com.waykichain.chain.commons.biz.xservice.AccountXservice
import com.waykichain.chain.po.v2.*
import com.waykichain.chain.vo.v2.*
import com.waykichain.commons.base.BizResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * @Author: yanjunlin
 * @CreateDate: 2019/4/4 12:05
 * @Description: 用户地址相关
 */

@RestController
@RequestMapping("/account")
@Api(description = "account|账户相关")
class AccountController {

    @PostMapping("/getaccountinfo")
    @ApiOperation(value="【Get the details of account】【获取账户的详细信息】",
                  notes="",
                  httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun accountInfo(@RequestBody @Valid po : QueryAccountDetailPO): BizResponse<AccountDetailVO> {
        return accountXservice.getAccountInfo(po)
    }

//    @PostMapping("/balance")
//    @ApiOperation(value= "账户余额", notes="账户余额", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
//    fun accountBalance(@RequestBody @Valid po: QueryAccountDetailPO): BizResponse<AccountBalanceVO> {
//        return accountXservice.getAccountBalance(po)
//    }

    @PostMapping("/validateaddr")
    @ApiOperation(value="【Check if the account address is valid】【检查账户地址是否有效】",
            notes="",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun validateAddress(@RequestBody @Valid po: ValidateAddressPO): BizResponse<ValidateAddressVO> {
        return accountXservice.validateAddress(po)
    }

    @PostMapping("/genregisteraccountraw")
    @ApiOperation(value="【Generate signature for register account】【创建激活账户交易的签名】",
            notes="Please ensure that there is a corresponding private key in the Baas wallet node  \n" +
                    "请确保Baas钱包节点中存在激活者私钥",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun genRegisterAccountraw(@RequestBody @Valid po: GenRegisterAccountrawPO): BizResponse<GenRegisterAccountrawVO> {
        return accountXservice.genRegisterAccountraw(po)
    }

    @PostMapping("/importprivkey")
    @ApiOperation(value="【Import the private key into the Baas background wallet】【将私钥导入Baas后台钱包】",
            notes="【Note: Baas wallet node will save your private key, please use with caution】This API call import the private key into the Baas background wallet. Please ensure this API call is executed in a secure environment.  \n" +
            "【注意：Baas钱包节点将会保存您的私钥,请慎用】此API调用将私钥导入Baas后台钱包,请确保在安全的环境中执行此API调用。",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun importPrivkey(@RequestBody @Valid po: ImportPrivateKeyPO): BizResponse<ImportPrivateKeyVO> {
        return accountXservice.importPrivkey(po)
    }

    @PostMapping(value = "/registeraccounttx")
    @ApiOperation(value = "【register account】【激活账户】",
            notes = "Please ensure that there is a corresponding private key in the Baas wallet node  \n" +
                    "请确保Baas钱包节点中存在激活者私钥",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun registerAccountTx(@RequestBody @Valid po: QueryAccountDetailPO) :BizResponse<RegisterAccountVO> {
        return accountXservice.registerAccountTx(po)
    }

    @PostMapping("/getnewaddress")
    @ApiOperation(value= "【Generate new account(address)】【创建新账户(地址)】",
            notes="" +
            "【Note: Baas wallet node will save your private key, please use with caution】This API call returns the randomly generated privatekey value. Please ensure this API call is executed in a secure environment.  \n" +
            "【注意：Baas钱包节点将会保存您的私钥,请慎用】此API调用返回随机生成的私钥,请确保在安全的环境中执行此API调用。",
            httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun newAddress(): BizResponse<NewAddressVO> {
        return accountXservice.getNewaddress()
    }

//    @PostMapping("/balancebylog")
//    @ApiOperation(value= "通过log查询余额", notes="通过log查询余额", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
//    fun newAddress(@RequestBody @Valid po: QueryAccountDetailPO): BizResponse<AccountBalanceVO> {
//        return accountXservice.getBalanceByLog(po)
//    }


/*    @PostMapping("/totalcoin")
    @ApiOperation(value= "获取所有的WICC数量", notes="获取所有的WICC数量", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    fun totalCoin(): BizResponse<TotalCoinVO> {
        return accountXservice.getTotalCoin()
    }*/



    @Autowired
    lateinit var accountXservice: AccountXservice
}

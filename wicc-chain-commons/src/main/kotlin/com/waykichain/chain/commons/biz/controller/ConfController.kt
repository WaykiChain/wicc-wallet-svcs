package com.waykichain.chain.commons.biz.controller


import com.waykichain.commons.base.BizResponse
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(hidden = true)
@RestController
@RequestMapping("/ops")
class ConfController {
    @Autowired lateinit var sysConfigLoadManager: com.waykichain.chain.commons.biz.configuration.SysConfigLoadManager

    @GetMapping("/reload")
    fun reload(): String {
        sysConfigLoadManager.init()
        return "SUCCESS"
    }

    @GetMapping("/ver")
    fun getVersion(): BizResponse<String> {
        return BizResponse("0.500.6-SNAPSHOT")
    }
}
package com.waykichain.chain.commons.biz.xservice

import com.dingtalk.chatbot.DingtalkChatbotClient
import com.dingtalk.chatbot.message.TextMessage
import com.waykichain.commons.util.ExceptionUtils
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 * Created by Joss on 10/16/18.
 */

@Service("dingTalkService")
class DingTalkService {

   var dingtalkChatbotClient = DingtalkChatbotClient()

    public fun sendTextMessage( url:String, param:String){
        var message = TextMessage(param)
        try {
            dingtalkChatbotClient.send(url,message)
        } catch (e:Exception) {
            logger.error(ExceptionUtils.getErrorInfoFromException(e))
            logger.error("发送失败")
        }
    }
    var logger = LoggerFactory.getLogger(javaClass)
}

package com.waykichain.chain.commons.biz.xservice

import com.waykichain.chain.biz.domain.BcWiccAccountCheckBatch
import org.slf4j.LoggerFactory

/**
 * Created by Joss on 05/16/18.
 */

interface AccountCheckXService {

   fun onCheck(bcWiccAccountCheckBatch: BcWiccAccountCheckBatch)

   fun onAjustAccountLog(batch: BcWiccAccountCheckBatch?)

}
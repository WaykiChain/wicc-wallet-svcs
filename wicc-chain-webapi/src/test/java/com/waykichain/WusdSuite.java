package com.waykichain;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        WusdSuperViserOperationTest.class,
        WusdAdminTokenTest.class,
        WusdUserOperationTest.class,
        WusdOperateUserByAdmin.class,
        WusdAdminGameTest.class,
        WusdLotteryByAdmin.class
})
public class WusdSuite {
}

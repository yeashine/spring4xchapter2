package cn.litten.service;

import cn.litten.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration("classpath*:/spring-config.xml")
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    @Autowired
    private UserService userService;

   /* @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }*/

    @Test
    public void userServiceTest(){
        boolean hasMatchUser = userService.hasMatchUser("admin", "123456");
        boolean hasMatchUser1 = userService.hasMatchUser("admin", "12");
        Assert.assertTrue(hasMatchUser);
        Assert.assertTrue(!hasMatchUser1);

        User user = userService.findUserByUsername("admin");
        Assert.assertNotNull(user);
    }

}

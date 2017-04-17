package org.ksyzz.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ksyzz.repository.AccountRepository;
import org.ksyzz.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by fengqian on 2017/4/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class MainTest {
//
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;
    @Test
    public void testGetAccount(){
//        Account account1 = new Account();
//        account1.setId("ksyzz");
//        account1.setUserName("fe");
//        account1.setPassword("123456");
//        account1.setAccountType(AccountType.TEACHER);
//        accountRepository.save(account1);
//
// ;
        given()
                .param("id", "ksyzz4")
                .param("password", "123456")
                .param("userName", "ksyzz")
                .param("account_type", "teacher")
                .when()
                .post("/account/register")
                .then()
                .assertThat()
                .statusCode(200);
    }
}

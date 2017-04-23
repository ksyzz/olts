package org.ksyzz.test;

import com.jayway.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ksyzz.repository.AccountRepository;
import org.ksyzz.service.AccountService;
import org.ksyzz.service.AccountTokenService;
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
    @Autowired
    AccountTokenService accountTokenService;
    @Test
    public void testCreateAccount(){

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
    @Test
    public void testDeleteToken(){
//        Account account = accountService.findById("ksyzz4");
//        String token = accountTokenService.createToken(account);
        given()
                .header("token", "123456")
                .contentType(ContentType.JSON)
                .body("{\"description\":\"2\",\"questionType\":\"RADIO\"}")
                .when()
                .post("/question/add")
                .then()
                .assertThat()
                .statusCode(200);
    }
}

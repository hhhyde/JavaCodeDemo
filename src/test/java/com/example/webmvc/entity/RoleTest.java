package com.example.webmvc.entity;

import com.example.webmvc.dao.RoleDao;
import com.example.webmvc.dao.UsersMapper;
import com.example.webmvc.web.DemoApplication;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(DemoApplication.class)
public class RoleTest {
    private static Validator validator;

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UsersMapper usersMapper;

    @Value("${mode}")
    private String mode;


    @Test
    public void userIsExist(){
        System.out.println(mode);
        Users users = usersMapper.selectByPrimaryKey("admin");
        Assert.assertNotNull(users);
    }
}

package com.example.webmvc.entity;

import com.example.webmvc.dao.RoleDao;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by Administrator on 2017/1/23.
 */
@ContextConfiguration("classpath:spring/spring-*.xml")
public class RoleTest {
    private static Validator validator;

    @Autowired
    private RoleDao roleDao;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void roleTooShort() {
        Role role = new Role();
        role.setRoleid(222L);
        Set<ConstraintViolation<Role>> constraintViolations = validator.validate(role);
        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals("roleID too short", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void roleMapperText(){
        Role role = roleDao.selectByPrimaryKey(75L);
        Assert.assertNotNull(role);
    }
}

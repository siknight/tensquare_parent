package com;

import com.tensquare.base.BaseApplication;
import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.service.LabelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BaseApplication.class})
public class test {

    @Autowired
    LabelService labelService;

    @Test
    public void test01(){
        labelService.findAll();
    }
}

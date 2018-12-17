package com.baizhi;

import com.baizhi.entity.Address;
import com.baizhi.entity.User;
import com.baizhi.entity.UserExample;
import com.baizhi.mapper.UserMapper;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootCommonMapperApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Test
    public void testSelectAll() {
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void testSelectByPrimaryKey() {
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user);
    }

    @Test
    public void testSelect(){
        User t = new User();
        t.setId(1);
        t.setName("翠花");
        t.setUserAge(1);
        Address address = new Address();
        address.setId("1");
        t.setAddress(address);
        List<User> select = userMapper.select(t);
        for (User user1 : select) {
            System.out.println(user1);
        }
    };

    @Test
    public void testSelectCount(){
        int i = userMapper.selectCount(new User());
        System.out.println(i);
    }

    @Test
    public void testInsert(){
        userMapper.insert(new User("dfds",1));
    }

    @Test
    public void testDeleteByPrimaryKey(){
        int i = userMapper.deleteByPrimaryKey(1);
        System.out.println(i);
    }

    @Test
    public void testUpdateByPrimaryKey(){
        User user = new User(6,"翠花", 1,null);
        userMapper.updateByPrimaryKey(user);
    }

    @Test
    public void testLimit(){
        User user = new User(null,null, 1,null);
        RowBounds rowBounds = new RowBounds(4,4);
        List<User> users = userMapper.selectByRowBounds(user, rowBounds);
        for (User user1 : users) {
            System.out.println(user1);
        }
        System.out.println(users.size());
    }

    //qbc
    @Test
    public void testExample(){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdBetween(2L,7L);
        userExample.or().andIdLessThan(2L);
        List<User> users = userMapper.selectByExample(userExample);
        for (User user : users) {
            System.out.println(user);
        }
    }

}


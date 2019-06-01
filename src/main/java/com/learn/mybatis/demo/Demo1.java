package com.learn.mybatis.demo;

import com.learn.mybatis.mapper.LearnResourceMapper;
import com.learn.mybatis.pojo.LearnResource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @author : gaohui
 * @Date : 2019/3/26 09:18
 * @Description :
 * <p>
 * 入门程序
 */

/**
 *
 */

/**
 * 踩坑
 * 路径不能使用 classpath:mybatis-config.xml
 */
public class Demo1 {


    private static final String resource = "mybatis-config.xml";

    public static void main(String[] args) throws Exception {

        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        SqlSession sqlSession = sessionFactory.openSession();
        

        LearnResourceMapper mapper = sqlSession.getMapper(LearnResourceMapper.class);

        LearnResource learnResource = mapper.selectByPrimaryKey(999L);
        System.out.println(learnResource);

        Object o = sqlSession.selectOne("com.learn.mybatis.mapper.LearnResourceMapper.selectByPrimaryKey", 999L);

        System.out.println(o);
        sqlSession.close();
    }


}

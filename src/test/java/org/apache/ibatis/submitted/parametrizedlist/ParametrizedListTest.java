/**
 *    Copyright 2009-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.submitted.parametrizedlist;

import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ParametrizedListTest {

  private SqlSessionFactory sqlSessionFactory;

  @BeforeMethod
  public void setUp() throws Exception {
    Connection conn = null;

    try {
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4000/test", "root", "");

      Reader reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/parametrizedlist/CreateDB.sql");

      ScriptRunner runner = new ScriptRunner(conn);
      runner.setLogWriter(null);
      runner.setErrorLogWriter(new PrintWriter(System.err));
      runner.runScript(reader);
      conn.commit();
      reader.close();

      reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/parametrizedlist/Config.xml");
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
      reader.close();
    } finally {
      if (conn != null) {
        conn.close();
      }
    }
  }

  @Test(groups={"tidb"})
  public void testShouldDetectUsersAsParameterInsideAList() throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      Mapper mapper = sqlSession.getMapper(Mapper.class);
      List<User<String>> list = mapper.getAListOfUsers();
      Assert.assertEquals(User.class, list.get(0).getClass());
    } finally {
      sqlSession.close();
    }
  }

  @Test(groups={"tidb"})
  public void testShouldDetectUsersAsParameterInsideAMap() throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      Mapper mapper = sqlSession.getMapper(Mapper.class);
      Map<Integer, User<String>> map = mapper.getAMapOfUsers();
      Assert.assertEquals(User.class, map.get(1).getClass());
    } finally {
      sqlSession.close();
    }
  }

  @Test(groups={"tidb"})
  public void testShouldGetAUserAsAMap() throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      Mapper mapper = sqlSession.getMapper(Mapper.class);
      Map<String, Object> map = mapper.getUserAsAMap();
      Assert.assertEquals(1, map.get("id"));
    } finally {
      sqlSession.close();
    }
  }

  @Test(groups={"tidb"})
  public void testShouldGetAListOfMaps() throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      Mapper mapper = sqlSession.getMapper(Mapper.class);
      List<Map<String, Object>> map = mapper.getAListOfMaps();
      Assert.assertEquals(1, map.get(0).get("id"));
    } finally {
      sqlSession.close();
    }
  }

}

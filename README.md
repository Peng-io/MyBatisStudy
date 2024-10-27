# mybatis 学习笔记

## Maven静态资源过滤问题

```xml

<build>
    <resources>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>false</filtering>
        </resource>
        <resource>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>false</filtering>
        </resource>
    </resources>
</build>
```

# mybatis 实现增删改查

## 实体类

```java
public class User {
    private String id;
    private String name;
    private String password;
    //构造,有参,无参
    //set/get
    //toString()
}
```

## Mapper 接口

```java
public interface UserMapper {
    // 查询全部
    List<User> selectUser();

    // 根据id 查询
    User selectUserById(String id);

    User selectUserById2(Map<String, Object> map);

    //添加用户
    int addUser(User user);

    //更新用户信息
    int updateUser(User user);

    //删除用户
    int deleteUser(String id);
}
```

## mybatis-Config.xml 配置项

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url"
                          value="jdbc:mysql://localhost:3306/study?useSSL=true&amp;useUnicode=true&amp;characterEncoding=utf8"/>
                <!--                数据库用户名-->
                <property name="username" value="root"/>
                <!--                连接密码-->
                <property name="password" value="root"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <!--    注册Mapper-->
        <mapper resource="com/demo/Mapper/UserMapper.xml"/>
    </mappers>
</configuration>
```

## mybatis UserMapper.xml 配置项

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- namespace= 绑定一个对应的DAO/mapper接口-->
<mapper namespace="com.demo.Mapper.UserMapper">
    <!--    id= 对应接口里的方法名-->
    <!--    resultType= 结果集类型-->
    <select id="selectUser" resultType="com.demo.pojo.User">
        select *
        from user
    </select>
</mapper>
```

## select

```text
List<User> selectUser();
```

```xml
<!--    id= 对应接口里的方法名-->
<!--    resultType= 结果集类型-->
<select id="selectUser" resultType="com.demo.pojo.User">
    select *
    from user
</select>
```

```java
public class mybatisTest {
    @Test
    public void selectUser() {
        SqlSession session = MybatisUtils.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<User> userList = mapper.selectUser();
        for (User user : userList) {
            System.out.println(user);
        }
        session.close();
    }
}

```

### 带参数的查询

```text
User selectUserById(String id);
```

```xml
 <!--    parameterType=  参数类型-->
<select id="selectUserById" parameterType="String" resultType="com.demo.pojo.User">
    select *
    from user
    where id = #{id}
</select>
```

```java
public class mybatisTest {
    @Test
    public void selectUserById() {
        SqlSession session = MybatisUtils.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.selectUserById("0001");
        System.out.println(user);
    }
}
```

```text
parameterType="String" 对应 User selectUserById(String id) 的参数类型
在执行时会把 id的值替换sql里的#{id}
```

### 多值查询

```text
User selectUserById2(Map<String, Object> map);
```

```xml

<select id="selectUserById2" parameterType="map" resultType="com.demo.pojo.User">
    select *
    from user
    where id = #{id}
    and name = #{name}
</select>
```

```java
public class mybatisTest {
    @Test
    public void selectUserById2() {
        SqlSession session = MybatisUtils.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("id", "0001");
        map.put("name", "digua");
        User user = mapper.selectUserById2(map);
        System.out.println(user);
    }
}
```

```text
在接口方法中，参数直接传递Map。
User selectUserById2(Map<String, Object> map);
编写sql语句的时候，需要传递参数类型，参数类型为map。
在使用方法的时候，Map的 key 为 sql中取的值即可，没有顺序要求！
```

## insert

```text
int addUser(User user);
```

```xml

<insert id="addUser" parameterType="com.demo.pojo.User">
    insert into user(id, name, password)
    values (#{id}, #{name}, #{password})
</insert>
```

```java
public class mybatisTest {
    @Test
    public void addUser() {
        SqlSession session = MybatisUtils.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        int i = mapper.addUser(new User("0002", "李四", "123456"));
        if (i > 0) {
            System.out.println("插入成功");
            session.commit();
        }
        session.close();
    }
}
```

## update

```text
int updateUser(User user);
```

```xml

<update id="updateUser" parameterType="com.demo.pojo.User">
    update user
    set name = #{name},
    password=#{password}
    where id = #{id}
</update>
```

```java
public class mybatisTest {
    @Test
    public void updateUser() {
        SqlSession session = MybatisUtils.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        int i = mapper.updateUser(new User("0001", "digua", "123456"));
        if (i > 0) {
            System.out.println("修改成功");
            session.commit();
        }
    }
}
```

## delete

```text
int deleteUser(String id);
```

```xml

<delete id="deleteUser" parameterType="String">
    delete
    from user
    where id = #{id}
</delete>
```

```java
public class mybatisTest {
    @Test
    public void deleteUser() {
        SqlSession session = MybatisUtils.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        int i = mapper.deleteUser("0002");
        if (i > 0) {
            System.out.println("删除成功");
            session.commit();
        }
    }
}
```

# 结果映射 ResultMap

```text
结果集映射用于实体类属性名与数据库字段名不同时，对其进行映射
```

```xml

<resultMap id="UserMap" type="com.demo.pojo.User">
    <!-- column是数据库表的列名 , property是对应实体类的属性名 -->
    <id column="id" property="id"/>
    <result column="name" property="name"/>
    <result column="password" property="pwd"/>
</resultMap>
```

```xml

<select id="selectUserById" resultMap="UserMap">
    select id , name , password from user where id = #{id}
</select>
```

## limit实现分页

```text
#语法
SELECT * FROM table LIMIT stratIndex，pageSize
SELECT * FROM table LIMIT 5,10; // 检索记录行 6-15   
#为了检索从某一个偏移量到记录集的结束所有的记录行，可以指定第二个参数为 -1：    
SELECT * FROM table LIMIT 95,-1; // 检索记录行 96-last.   
#如果只给定一个参数，它表示返回最大的记录行数目：    
SELECT * FROM table LIMIT 5; //检索前 5 个记录行   
#换句话说，LIMIT n 等价于 LIMIT 0,n。
```

# 嵌套查询

```java

//GET,SET,ToString，有参，无参构造
public class Teacher {
    private int id;
    private String name;
}
```

```java

//GET,SET,ToString，有参，无参构造
public class Student {
    private int id;
    private String name;
    //多个学生可以是同一个老师，即多对一
    private Teacher teacher;
}
```

```java

public interface StudentMapper {
    List<Student> getStudents();

    List<Student> getStudents2();
}
```

```java
public interface TeacherMapper {
    Teacher getTeacher(@Param("tid") int id);
}
```

## 按查询嵌套处理

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.demo.mapper.StudentMapper">
    <!--    按查询嵌套处理-->

    <select id="getStudents" resultMap="StudentTeacher">
        select s.id sid, s.name sname, tid
        from student s;
    </select>

    <select id="getTeacher" resultType="Teacher">
        select *
        from teacher
        where id =
        #{tid}
    </select>

    <resultMap id="StudentTeacher" type="Student">
        <result property="id" column="sid"/>
        <result property="name" column="sname"/>
        <association property="teacher" column="tid" javaType="Teacher" select="getTeacher"/>
    </resultMap>
</mapper>
```

## 按结果嵌套处理

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.demo.mapper.StudentMapper">
    <!--    按结果嵌套处理-->
    <resultMap id="StudentTeacher2" type="Student">
        <result property="id" column="sid"/>
        <result property="name" column="sname"/>
        <!--关联对象property 关联对象在Student实体类中的属性-->
        <association property="teacher" javaType="Teacher">
            <result property="id" column="tid"/>
            <result property="name" column="tname"/>
        </association>
    </resultMap>

    <select id="getStudents2" resultMap="StudentTeacher2">
        select s.id sid, s.name sname, s.tid, t.name tname
        from student s,
        teacher t
        where s.tid = t.id
    </select>
</mapper>
```


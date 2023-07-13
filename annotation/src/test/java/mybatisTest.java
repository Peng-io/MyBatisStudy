import com.demo.Mapper.UserMapper;
import com.demo.pojo.User;
import com.demo.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void selectUserById() {
        SqlSession session = MybatisUtils.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.selectUserById("0001");
        System.out.println(user);
    }


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

    @Test
    public void updateUser() {
        SqlSession session = MybatisUtils.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        int i = mapper.updateUser(new User("0001", "地瓜", "123456"));
        if (i > 0) {
            System.out.println("修改成功");
            session.commit();
        }
    }

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

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

        int currentPage = 2;  //第几页
        int pageSize = 3;  //每页显示几个
        Map<String, Integer> map = new HashMap<>();
        map.put("startIndex", (currentPage - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<User> userList = mapper.selectUser(map);
        for (User user : userList) {
            System.out.println(user);
        }
        session.close();
    }
}

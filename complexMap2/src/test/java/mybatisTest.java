import com.demo.mapper.StudentMapper;
import com.demo.mapper.TeacherMapper;
import com.demo.pojo.Student;
import com.demo.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;


public class mybatisTest {
    @Test
    public void selectUser() {
        SqlSession session = MybatisUtils.getSession();
        TeacherMapper mapper = session.getMapper(TeacherMapper.class);
        System.out.println(mapper.getTeacher(1));
        session.close();
    }

    @Test
    public void selectUser2() {
        SqlSession session = MybatisUtils.getSession();
        TeacherMapper mapper = session.getMapper(TeacherMapper.class);
        System.out.println(mapper.getTeacher2(1));
        session.close();
    }


}

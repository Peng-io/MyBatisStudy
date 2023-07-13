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
        StudentMapper mapper = session.getMapper(StudentMapper.class);
        for (Student student : mapper.getStudents()) {
            System.out.println(student);
        }
        session.close();
    }

    @Test
    public void selectUser2() {
        SqlSession session = MybatisUtils.getSession();
        StudentMapper mapper = session.getMapper(StudentMapper.class);
        for (Student student : mapper.getStudents2()) {
            System.out.println(student);
        }
        session.close();
    }
    

}

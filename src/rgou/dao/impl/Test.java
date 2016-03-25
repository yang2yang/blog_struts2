package rgou.dao.impl;

/**
 * Created by jack on 16-3-25.
 */

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import rgou.dao.vo.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test {
    public static void main(String[] args) throws IOException {
        String resource = "mybits.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = ssf.openSession();
        try{
            User user = (User) session.selectOne("selectUser", "hello");
            System.out.println(user.getUsername());
            System.out.println("--------------分隔线---------------");

            List<User> users = session.selectList("selectUsers");
            for(int i=0; i<users.size(); i++) {
                System.out.println(users.get(i).getUsername());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
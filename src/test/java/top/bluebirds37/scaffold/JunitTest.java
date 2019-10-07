package top.bluebirds37.scaffold;

import top.bluebirds37.scaffold.config.authority.JWTUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class JunitTest {

    @Test
    public void testCreate() {

        String sign = JWTUtil.sign(1, 1L);
        boolean verify = JWTUtil.verify(sign);
        Integer userId = JWTUtil.getUserId(sign);

    }

}

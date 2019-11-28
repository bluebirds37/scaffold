package top.bluebirds37.scaffold.repository;

import top.bluebirds37.scaffold.pojo.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /**
     * 用户民查询
     * @param username
     * @return
     */
    User findByUsername(String username);

}

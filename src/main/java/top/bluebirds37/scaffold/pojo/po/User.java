package top.bluebirds37.scaffold.pojo.po;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_user")
@org.hibernate.annotations.Table(appliesTo = "t_user", comment = "用户")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11) comment '主键'")
    private Integer id;

    @Column(name = "username", columnDefinition = "varchar(255) comment '用户'")
    private String username;

    @Column(name = "password", columnDefinition = "varchar(255) comment '密码'")
    private String password;

    @Column(name = "phone", columnDefinition = "varchar(255) comment '注册手机号'")
    private String phone;

    @Column(name = "email", columnDefinition = "varchar(255) comment '注册邮箱'")
    private String email;

    @JoinColumn(name = "type_id", columnDefinition = "int(11) comment '用户类型'")
    private Dictionary dictionary;

    @ManyToMany(targetEntity = Role.class, mappedBy = "users")
    private Set<Role> roles = new LinkedHashSet<>();

}
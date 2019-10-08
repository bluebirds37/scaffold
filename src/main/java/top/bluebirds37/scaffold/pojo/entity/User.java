package top.bluebirds37.scaffold.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name = "id", columnDefinition = "bigint comment '主键'")
    private Integer id;

    @Column(name = "username", columnDefinition = "varchar(255) comment '用户'")
    private String username;

    @Column(name = "password", columnDefinition = "varchar(255) comment '密码'")
    private String password;

    @Column(name = "phone", columnDefinition = "varchar(255) comment '注册手机号'")
    private String phone;

    @Column(name = "email", columnDefinition = "varchar(255) comment '注册邮箱'")
    private String email;

    @ManyToMany(targetEntity = Role.class, mappedBy = "users")
    private Set<Role> roles = new LinkedHashSet<>();

}
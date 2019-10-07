package top.bluebirds37.scaffold.pojo.dto;

import top.bluebirds37.scaffold.pojo.entity.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto implements Serializable {

    private Long id;

    @ApiModelProperty(value = "用户")
    private String username;

    @ApiModelProperty(value = "密码")
    @Column(name = "password", columnDefinition = "varchar(255) comment '密码'")
    private String password;

    @ApiModelProperty(value = "注册手机号")
    @Column(name = "phone", columnDefinition = "varchar(255) comment '注册手机号'")
    private String phone;

    @ApiModelProperty(value = "注册邮箱")
    @Column(name = "email", columnDefinition = "varchar(255) comment '注册邮箱'")
    private String email;

    @ApiModelProperty(value = "角色")
    @ManyToMany(targetEntity = Role.class, mappedBy = "users")
    private Set<Role> roles = new LinkedHashSet<>();

}
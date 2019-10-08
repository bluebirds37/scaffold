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
@Table(name = "t_role")
@org.hibernate.annotations.Table(appliesTo = "t_role", comment = "权限")
public class Role implements Serializable {

    @Id
    @Column(name = "id", columnDefinition = "bigint comment '主键'")
    private Integer id;

    @JoinColumn(name = "parent_id", columnDefinition = "bigint comment '父角色'")
    @ManyToOne(targetEntity = Role.class)
    private Role parentRole;

    @Column(name = "name", columnDefinition = "varchar(255) comment '角色名称'")
    private String name;

    @JoinColumn(name = "type_id", columnDefinition = "varchar(255) comment '角色英文名称'")
    private Dictionary dictionary;

    @Column(name = "description", columnDefinition = "varchar(255) comment '备注'")
    private String description;

    @JoinColumn(name = "parent_id", columnDefinition = "bigint comment '父角色'")
    @OneToMany(targetEntity = Role.class)
    private Set<Role> childRoles = new LinkedHashSet<>();


    /*
     *   @JoinTable注解添加中间表:其中name属性设置中间表的表名
     *   @JoinColumns属性:joinColumn属性设置中间表中的列名,referencedColumnName属性指向被映射表的主键（可以没有该属性）
     *   @inverseJoinColumns:另外一张表在中间表中的列
     */

    @JoinTable(
            name = "t_user_role",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}
    )
    @ManyToMany
    private Set<User> users = new LinkedHashSet<>();

    @ManyToMany(targetEntity = Permission.class, mappedBy = "roles")
    private Set<Permission> permissions = new LinkedHashSet<>();


}
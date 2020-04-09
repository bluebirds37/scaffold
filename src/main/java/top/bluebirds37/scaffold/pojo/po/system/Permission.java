package top.bluebirds37.scaffold.pojo.po.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_permission")
@org.hibernate.annotations.Table(appliesTo = "t_permission", comment = "权限")
public class Permission implements Serializable {

    @Id
    @GeneratedValue(generator = "snowFlake")
    @GenericGenerator(name = "snowFlake", strategy = "top.bluebirds37.scaffold.config.jpa.SnowIdentityGenerator")
    @Column(name = "id", columnDefinition = "varchar(255) comment '主键'")
    private String id;

    @JoinColumn(name = "parent_id", columnDefinition = "varchar(255) comment '父权限'")
    @ManyToOne(targetEntity = Permission.class)
    private Permission permission;

    @Column(name = "name", columnDefinition = "varchar(255) comment '权限名称'")
    private String name;

    @Column(name = "url", columnDefinition = "varchar(255) comment '接口地址'")
    private String url;

    @JoinColumn(name = "type_id", columnDefinition = "varchar(255) comment '类型'")
    @ManyToOne(targetEntity = Dictionary.class)
    private Dictionary type;

    @Column(name = "description", columnDefinition = "varchar(255) comment '备注'")
    private String description;

    @JoinColumn(name = "parent_id", columnDefinition = "varchar(255) comment '父权限'")
    @OneToMany(targetEntity = Permission.class)
    private Set<Permission> childPermissions = new LinkedHashSet<>();

    /*
     *   @JoinTable注解添加中间表:其中name属性设置中间表的表名
     *   @JoinColumns属性:joinColumn属性设置中间表中的列名,referencedColumnName属性指向被映射表的主键（可以没有该属性）
     *   @inverseJoinColumns:另外一张表在中间表中的列
     */

    @JoinTable(
            name = "t_role_permission",
            joinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    @ManyToMany
    private Set<Role> roles = new LinkedHashSet<>();


}
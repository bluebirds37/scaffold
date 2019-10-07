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
@Table(name = "t_permission")
@org.hibernate.annotations.Table(appliesTo = "t_permission",comment = "权限")
public class Permission implements Serializable {

    @Id
    @Column(name = "id",columnDefinition = "bigint comment '主键'")
    private Integer id;

    @Column(name = "parent_id",columnDefinition = "bigint comment '父权限'")
    private Integer parentId;

    @Column(name = "name",columnDefinition = "varchar(255) comment '权限名称'")
    private String name;

    @Column(name = "url",columnDefinition = "varchar(255) comment '接口地址'")
    private String url;

    @Column(name = "security_name",columnDefinition = "varchar(255) comment '权限英文名称'")
    private String securityName;

    @Column(name = "description",columnDefinition = "varchar(255) comment '备注'")
    private String description;

    /*
     *   @JoinTable注解添加中间表:其中name属性设置中间表的表名
     *   @JoinColumns属性:joinColumn属性设置中间表中的列名,referencedColumnName属性指向被映射表的主键（可以没有该属性）
     *   @inverseJoinColumns:另外一张表在中间表中的列
     */

    @JoinTable(
            name="t_role_permission",
			joinColumns = {@JoinColumn(name="permission_id",referencedColumnName="id")},
			inverseJoinColumns= {@JoinColumn(name="role_id", referencedColumnName="id")}
    )
    @ManyToMany
    private Set<Role> roles = new LinkedHashSet<>();


}
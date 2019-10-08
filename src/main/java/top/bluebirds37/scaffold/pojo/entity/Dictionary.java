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
@Table(name = "t_dictionary")
@org.hibernate.annotations.Table(appliesTo = "t_dictionary", comment = "字典表")
public class Dictionary implements Serializable {

    @Id
    @Column(name = "id", columnDefinition = "bigint comment '主键'")
    private Integer id;

    @JoinColumn(name = "parent_id", columnDefinition = "bigint comment '父id'")
    @ManyToOne(targetEntity = Dictionary.class)
    private Dictionary parentDictionary;

    @Column(name = "name", columnDefinition = "varchar(255) comment '权限名称'")
    private String name;

    @Column(name = "`value`", columnDefinition = "varchar(255) comment '标识值'")
    private String value;

    @Column(name = "description", columnDefinition = "varchar(255) comment '备注'")
    private String description;

    @Column(name = "sort", columnDefinition = "int(11) comment '排序'")
    private Integer sort;

    /*
     *   @JoinTable注解添加中间表:其中name属性设置中间表的表名
     *   @JoinColumns属性:joinColumn属性设置中间表中的列名,referencedColumnName属性指向被映射表的主键（可以没有该属性）
     *   @inverseJoinColumns:另外一张表在中间表中的列
     */

    @OneToMany(targetEntity = Dictionary.class)
    @JoinColumn(name = "parent_id")
    @OrderBy("sort ASC")
    private Set<Dictionary> childDictionaries = new LinkedHashSet<>();


}
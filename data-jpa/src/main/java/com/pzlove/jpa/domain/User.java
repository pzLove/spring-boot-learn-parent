package com.pzlove.jpa.domain;

import lombok.*;

import javax.persistence.*;

/**
 * 声明的实体类
 *
 * @author pzlove
 * @date 2018-07-13 下午2:55
 **/
@Entity
@Table(name = "tb_user")
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
/**
 * TABLE：使用一个特定的数据库表格来保存主键。
 * SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
 * IDENTITY：主键由数据库自动生成（主要是自动增长型）
 * AUTO：主键由程序控制。
 * TABLE比较复杂，这里不讲解。分别介绍其他三个:
 */
public class User {
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键策略
    private Long id;
    @Column(name ="name",length = 100)
    private String name;
    @Column(name = "age",length = 20)
    private Integer age;
    @Column(name = "city",length = 100)
    private String city;

}

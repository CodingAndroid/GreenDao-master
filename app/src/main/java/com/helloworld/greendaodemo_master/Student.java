package com.helloworld.greendaodemo_master;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Entity 　表明这个实体类会在数据库中生成一个与之相对应的表。
 * @Id 　对应数据表中的 Id 字段，有了解数据库的话，是一条数据的唯一标识。
 * @Property(nameInDb = “STUDENTNUM”) 　表名这个属性对应数据表中的 STUDENTNUM 字段。
 * @Property 　可以自定义字段名，注意外键不能使用该属性
 * @NotNull 　该属性值不能为空
 * @Transient 　该属性不会被存入数据库中
 * @Unique 　表名该属性在数据库中只能有唯一值
 * Created by lihui1 on 2017/10/24.
 */

@Entity
public class Student {

    @Id(autoincrement = true)
    private Long stuId;

    @Index(unique = true)
    private String stuNo;

    private String stuName;

    private String stuSex;

    private String stuScore;

    @Generated(hash = 315497705)
    public Student(Long stuId, String stuNo, String stuName, String stuSex,
            String stuScore) {
        this.stuId = stuId;
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.stuSex = stuSex;
        this.stuScore = stuScore;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }

    public Long getStuId() {
        return this.stuId;
    }

    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }

    public String getStuNo() {
        return this.stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return this.stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuSex() {
        return this.stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public String getStuScore() {
        return this.stuScore;
    }

    public void setStuScore(String stuScore) {
        this.stuScore = stuScore;
    }
}

package com.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 学生实体
 *
 * @author <a href=mailto:2038322151@qq0=.com>Miraitowa_zcx</a>
 * @version 1.0.0
 * @since 2024/04/28
 */
@Setter
@Getter
public class Student {

    private int id;
    private String name;
    private String home;
    private String sex;
    private Float score;
    private Integer stuClassId;
    private String stuClassName;
    private String stuDesc;

    public Student(int id, String name, String home, String sex, Float score, Integer stuClassId, String stuDesc) {
        this.id = id;
        this.name = name;
        this.home = home;
        this.sex = sex;
        this.score = score;
        this.stuClassId = stuClassId;
        this.stuDesc = stuDesc;
    }

    public Student() {
    }

    public Student(String name, String home, String sex, Float score, Integer stuClassId, String stuDesc) {
        this.name = name;
        this.home = home;
        this.sex = sex;
        this.score = score;
        this.stuClassId = stuClassId;
        this.stuDesc = stuDesc;
    }

    public Student(String name, String home, Integer stuClassId) {
        this.name = name;
        this.home = home;
        this.stuClassId = stuClassId;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

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

    /**
     * 学生ID
     */
    private int id;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 学生家庭住址
     */
    private String home;

    /**
     * 学生性别
     */
    private String sex;

    /**
     * 学生成绩
     */
    private Float score;

    /**
     * 学生班级ID
     */
    private Integer stuClassId;

    /**
     * 学生班级名称
     */
    private String stuClassName;

    /**
     * 学生描述
     */
    private String stuDesc;

    /**
     * 构造方法
     */
    public Student() {
    }

    /**
     * 构造方法
     *
     * @param id         学生ID
     * @param name       学生姓名
     * @param home       学生家庭住址
     * @param sex        学生性别
     * @param score      学生成绩
     * @param stuClassId 学生班级ID
     * @param stuDesc    学生描述
     */
    public Student(int id, String name, String home, String sex, Float score, Integer stuClassId, String stuDesc) {
        this.id = id;
        this.name = name;
        this.home = home;
        this.sex = sex;
        this.score = score;
        this.stuClassId = stuClassId;
        this.stuDesc = stuDesc;
    }

    /**
     * 构造方法
     *
     * @param name       学生姓名
     * @param home       学生家庭住址
     * @param sex        学生性别
     * @param score      学生成绩
     * @param stuClassId 学生班级ID
     * @param stuDesc    学生描述
     */
    public Student(String name, String home, String sex, Float score, Integer stuClassId, String stuDesc) {
        this.name = name;
        this.home = home;
        this.sex = sex;
        this.score = score;
        this.stuClassId = stuClassId;
        this.stuDesc = stuDesc;
    }

    /**
     * 构造方法
     *
     * @param name       学生姓名
     * @param home       学生家庭住址
     * @param stuClassId 学生班级ID
     */
    public Student(String name, String home, Integer stuClassId) {
        this.name = name;
        this.home = home;
        this.stuClassId = stuClassId;
    }

    /**
     * 重写toString方法
     *
     * @return 学生姓名
     */
    @Override
    public String toString() {
        return this.name;
    }
}

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
public class StuGrade {

    /**
     * 编号
     */
    private int id;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 学生班级 ID
     */
    private Integer stuClassId;

    /**
     * 学生班级名称
     */
    private String stuClassName;

    /**
     * 语文成绩
     */
    private Float chineseScore;

    /**
     * 数学成绩
     */
    private Float mathScore;

    /**
     * 英语成绩
     */
    private Float englishScore;

    /**
     * 构造方法
     */
    public StuGrade() {
    }

    /**
     * 构造方法
     *
     * @param stuClassId   学生班级 ID
     * @param name         学生姓名
     * @param chineseScore 语文成绩
     * @param mathScore    数学成绩
     * @param englishScore 英语成绩
     */
    public StuGrade(Integer stuClassId, String name, Float chineseScore, Float mathScore, Float englishScore) {
        this.stuClassId = stuClassId;
        this.name = name;
        this.chineseScore = chineseScore;
        this.mathScore = mathScore;
        this.englishScore = englishScore;
    }

    /**
     * 构造方法
     *
     * @param name       学生姓名
     * @param stuClassId 学生班级 ID
     * @param number     编号
     */
    public StuGrade(String name, int number, int stuClassId) {
        this.name = name;
        this.stuClassId = stuClassId;
        this.id = number;
    }
}

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

    private int id;
    private String name;
    private Integer stuClassId;
    private String stuClassName;
    private Float chineseScore;
    private Float mathScore;
    private Float englishScore;

    public StuGrade() {
    }

    public StuGrade(Integer stuClassId, String name, Float chineseScore, Float mathScore, Float englishScore) {
        this.stuClassId = stuClassId;
        this.name = name;
        this.chineseScore = chineseScore;
        this.mathScore = mathScore;
        this.englishScore = englishScore;
    }

    public StuGrade(String name, int number, int stuClassId) {
        this.name = name;
        this.stuClassId = stuClassId;
        this.id = number;
    }
}

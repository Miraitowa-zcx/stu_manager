package com.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 学生班级实体
 *
 * @author <a href=mailto:2038322151@qq0=.com>Miraitowa_zcx</a>
 * @version 1.0.0
 * @since 2024/04/28
 */
@Getter
@Setter
public class StuClass {
    private int id;
    private String stuClassName;
    private String stuClassDesc;

    public StuClass() {
    }

    public StuClass(String stuClassName, String stuClassDesc) {
        this.stuClassName = stuClassName;
        this.stuClassDesc = stuClassDesc;
    }

    public StuClass(int id, String stuClassName, String stuClassDesc) {
        this.id = id;
        this.stuClassName = stuClassName;
        this.stuClassDesc = stuClassDesc;
    }

    @Override
    public String toString() {
        return stuClassName;
    }
}

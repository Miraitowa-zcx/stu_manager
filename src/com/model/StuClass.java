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

    /**
     * 主键
     */
    private int id;

    /**
     * 班级名称
     */
    private String stuClassName;

    /**
     * 班级描述
     */
    private String stuClassDesc;

    /**
     * 构造方法
     */
    public StuClass() {
    }

    /**
     * 构造方法
     *
     * @param stuClassName 班级名称
     * @param stuClassDesc 班级描述
     */
    public StuClass(String stuClassName, String stuClassDesc) {
        this.stuClassName = stuClassName;
        this.stuClassDesc = stuClassDesc;
    }

    /**
     * 构造方法
     *
     * @param id           主键
     * @param stuClassName 班级名称
     * @param stuClassDesc 班级描述
     */
    public StuClass(int id, String stuClassName, String stuClassDesc) {
        this.id = id;
        this.stuClassName = stuClassName;
        this.stuClassDesc = stuClassDesc;
    }

    /**
     * 重写toString方法
     *
     * @return 返回班级名称
     */
    @Override
    public String toString() {
        return stuClassName;
    }
}

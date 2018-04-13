package com.wechat.sell.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.wechat.sell.domain
 * @Description:
 *      @DynamicUpdate 注解作用 ：若使用该实体类保存查询出来的记录,做了修改后又存回数据库,注解可以自动修改当前updateTime
 *      @Data 注解： 实力类编译前自动加上 get/set及toString方法,让DTO更整洁
 * @author: Minsky
 * @date: 2018/3/25 22:19
 */
@Entity
@DynamicUpdate
public class ProductCategory {

    @Id
    @GeneratedValue
    private Integer categoryId;
    /**
     *  类目名
     */
    private String categoryName;

    /**
     *  类目编号
     */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryType=" + categoryType +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public ProductCategory(){}

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}

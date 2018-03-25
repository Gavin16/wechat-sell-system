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
@Data
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

    public ProductCategory(){}

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}

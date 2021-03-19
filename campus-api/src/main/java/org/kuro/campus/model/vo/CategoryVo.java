package org.kuro.campus.model.vo;

import lombok.Data;
import org.kuro.campus.model.entity.Category;

import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/4 14:45
 */
@Data
public class CategoryVo extends Category {

    private List<Category> children;
}

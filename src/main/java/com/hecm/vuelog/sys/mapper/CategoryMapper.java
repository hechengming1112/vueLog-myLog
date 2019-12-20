package com.hecm.vuelog.sys.mapper;

import com.hecm.vuelog.sys.domain.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {
    List<Category> getAllCategories();
    int addCategory(Category category);
    int updateCategoryById(Category category);
    int deleteCategoryByIds(@Param("ids") String[] ids);
}

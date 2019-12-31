package com.hecm.vuelog.sys.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagsMapper {
    int deleteTagsByAid(Long aid);

    int saveTags(@Param("tags") String[] tags);

    List<Long> getTagsIdByTagName(@Param("tagNames") String[] tagNames);

    int saveTags2ArticleTags(@Param("tagIds") List<Long> tagIds, @Param("aid") Long aid);
}

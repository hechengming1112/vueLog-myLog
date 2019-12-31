package com.hecm.vuelog.sys.mapper;

import com.hecm.vuelog.sys.domain.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    List<Article> getArticleByState (@Param("state") Integer state,@Param("start") Integer start, @Param("count") Integer count, @Param("uid") Long uid,@Param("keywords") String keywords);
    int getArticleCountByState(@Param("state") Integer state, @Param("uid") Long uid, @Param("keywords") String keywords);
    Article getArticleById(Long aid);

    void pvIncrement(Long aid);

    int addNewArticle(Article article);

    int updateArticle(Article article);
    int deleteArticleById(@Param("aids") Long[] aids);
    int updateArticleState(@Param("aids") Long aids[], @Param("state") Integer state);
    int updateArticleStateById(@Param("articleId") Integer articleId, @Param("state") Integer state);

}

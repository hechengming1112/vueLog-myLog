package com.hecm.vuelog.sys.service;

import com.hecm.vuelog.common.util.Util;
import com.hecm.vuelog.sys.domain.Article;
import com.hecm.vuelog.sys.mapper.ArticleMapper;
import com.hecm.vuelog.sys.mapper.TagsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    TagsMapper tagsMapper;


    public int addNewArticle(Article article){
        if (article.getSummary() == null || "".equals(article.getSummary())){
            String stripHtml=stripHtml(article.getHtmlContent());
            article.setSummary(stripHtml.substring(0,stripHtml.length()>50 ? 50 : stripHtml.length()));
        }
        if(article.getId() == -1){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            if (article.getState() ==1){
                //设置发表日期
                article.setPublishDate(timestamp);
            }
            article.setEditTime(timestamp);
            //设置当前用户
            article.setUid(Util.getCurrentUser().getId());
            int i = articleMapper.addNewArticle(article);
            //打标签
           String[] dynamicTags= article.getDynamicTags();
           if (dynamicTags != null && dynamicTags.length > 0){
               int tags = addTagsToArticle(dynamicTags, article.getId());
               if (tags == -1) {
                   return tags;
               }

           }
           return i;
        }else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            if (article.getState() == 1) {
                //设置发表日期
                article.setPublishDate(timestamp);
            }
            //更新
            article.setEditTime(new Timestamp(System.currentTimeMillis()));
            int i = articleMapper.updateArticle(article);
            //修改标签
            String[] dynamicTags = article.getDynamicTags();
            if (dynamicTags != null && dynamicTags.length > 0) {
                int tags = addTagsToArticle(dynamicTags, article.getId());
                if (tags == -1) {
                    return tags;
                }
            }
            return i;
        }


    }


    public List<Article> getArticleByState(Integer state,Integer page,Integer count,String keywords){
        int start=(page-1)*count;
        Long uid = Util.getCurrentUser().getId();
        return articleMapper.getArticleByState(state,start,count,uid,keywords);
    }
    public int getArticleCountByState(Integer state,Long uid,String keywords){
        return articleMapper.getArticleCountByState(state, uid,keywords);
    }
    public Article getArticleById(Long aid) {
        Article article = articleMapper.getArticleById(aid);
        articleMapper.pvIncrement(aid);
        return article;
    }

    public int updateArticleState(Long[] aids, Integer state) {
        if (state == 2) {
            return articleMapper.deleteArticleById(aids);
        } else {
            return articleMapper.updateArticleState(aids, 2);//放入到回收站中
        }
    }



   public String stripHtml(String content){
       content = content.replaceAll("<p .*?>", "");
       content = content.replaceAll("<br\\s*/?>", "");
       content = content.replaceAll("\\<.*?>", "");
       return content;
    }

    private int addTagsToArticle(String[] dynamicTags, Long aid) {
        //1.删除该文章目前所有的标签
        tagsMapper.deleteTagsByAid(aid);
        //2.将上传上来的标签全部存入数据库
        tagsMapper.saveTags(dynamicTags);
        //3.查询这些标签的id
        List<Long> tIds = tagsMapper.getTagsIdByTagName(dynamicTags);
        //4.重新给文章设置标签
        int i = tagsMapper.saveTags2ArticleTags(tIds, aid);
        return i == dynamicTags.length ? i : -1;
    }

    public int restoreArticle(Integer articleId) {
        return articleMapper.updateArticleStateById(articleId, 1); // 从回收站还原在原处
    }



}

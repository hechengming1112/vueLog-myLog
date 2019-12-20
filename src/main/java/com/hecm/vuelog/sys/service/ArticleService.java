package com.hecm.vuelog.sys.service;

import com.hecm.vuelog.common.util.Util;
import com.hecm.vuelog.sys.domain.Article;
import com.hecm.vuelog.sys.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    public List<Article> getArticleByState(Integer state,Integer page,Integer count,String keywords){
        int start=(page-1)*count;
        Long uid = Util.getCurrentUser().getId();
        return articleMapper.getArticleByState(state,start,count,uid,keywords);
    }
    public int getArticleCountByState(Integer state,Long uid,String keywords){
        return articleMapper.getArticleCountByState(state, uid,keywords);
    }
}

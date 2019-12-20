package com.hecm.vuelog.sys.concroller;

import com.hecm.vuelog.sys.domain.Article;
import com.hecm.vuelog.sys.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "/article/all",method = RequestMethod.GET)
    public Map<String,Object> getArticleByStateByAdmin(@RequestParam(value = "page",defaultValue = "1") Integer page,@RequestParam(value = "count",defaultValue = "6") Integer count,String keywords){
        List<Article> articles= articleService.getArticleByState(-2,page,count,keywords);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("articles",articles);
        map.put("totalCount",articleService.getArticleCountByState(1,null,keywords));

        return map;

    }


}
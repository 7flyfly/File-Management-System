package com.file.management.controller.IntegratedQuery;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.file.management.service.solr.SolrService;
import com.file.management.utils.SolrUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


/*
  综合查询
 */
@Controller
@RequestMapping("/IntegratedQuery")
public class IntegratedQuery {

    @RequestMapping("/QueryHomePage")
    //@RequiresPermissions("IntegratedQuery:QueryHomePage")//权限管理;
    public String integratedQuery(){
        return "IntegratedQuery/ControlCenter";
    }

    @RequestMapping("/IntelligentRetrieval")
    @RequiresPermissions("IntegratedQuery:IntelligentRetrieval")//权限管理;
    public String intelligentRetrieval(){
        return "IntegratedQuery/IntelligentRetrieval";
    }

    @RequestMapping("/AdvancedSearch")
    @RequiresPermissions("IntegratedQuery:AdvancedSearch")//权限管理;
    public String advancedSearch(){
        return "IntegratedQuery/AdvancedSearch";
    }

    @RequestMapping("/KnowledgeGraph")
    @RequiresPermissions("IntegratedQuery:KnowledgeGraph")//权限管理;
    public String KnowledgeGraph(){
        return "IntegratedQuery/KnowledgeGraph";
    }

}

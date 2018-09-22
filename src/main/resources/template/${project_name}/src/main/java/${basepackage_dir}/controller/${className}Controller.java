<#include "/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign remarks = table.remarks>
package ${basepackage}.controller;

import ${basepackage}.controller.request.${className}Request;
import ${basepackage}.service.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("${className}")
public class ${className}Controller {
    @Autowired
    private ${className}Service ${classNameLower}Service;

    @RequestMapping("get")
    @ResponseBody
    public Object get(<#list table.compositeIdColumns as column>${column.javaType} ${column.columnNameLower} <#if column_has_next> , </#if></#list>) {
        return userService.get(<#list table.compositeIdColumns as column>${column.columnNameLower} <#if column_has_next> , </#if></#list>);
    }

    @RequestMapping("getPage")
    @ResponseBody
    public Object getPage(${className}Request ${classNameLower}Request) {
        return ${classNameLower}Service.getPage(${classNameLower}Request);
    }

    @RequestMapping("insert")
    @ResponseBody
    public Object insert(${className}Request ${classNameLower}Request) {
        return ${classNameLower}Service.insert(${classNameLower}Request);
    }

    @RequestMapping("update")
    @ResponseBody
    public Object update(${className}Request ${classNameLower}Request) {
        return ${classNameLower}Service.update(${classNameLower}Request);
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(<#list table.compositeIdColumns as column>${column.javaType} ${column.columnNameLower} <#if column_has_next> , </#if></#list>) {
        return ${classNameLower}Service.delete(<#list table.compositeIdColumns as column>${column.columnNameLower} <#if column_has_next> , </#if></#list>);
    }
}

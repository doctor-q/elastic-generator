<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign remarks = table.remarks>
package ${basepackage}.service;

import ${basepackage}.controller.response.CommonResponse;
import com.google.common.base.Preconditions;
import ${basepackage}.controller.request.${className}Request;
import ${basepackage}.controller.response.CommonResponse;

<@classComment value="service接口"/>
public interface ${className}Service {
	/**
	 * 根据主键查询
     * 
     * @param id 主键
     * @return 响应体
	 */
	CommonResponse get(<#list table.compositeIdColumns as column>${column.javaType} ${column.columnNameLower} <#if column_has_next> , </#if></#list>);
	
	/**
	 * 根据查询条件查询单个数据
     * 
     * @param request 查询条件
     * @return 响应体分页列表
	 */
	CommonResponse getPage(${className}Request request);
	
	/**
	 * 根据主键更新数据
     * 
     * @param request 更新对象
	 */
	CommonResponse update(${className}Request request);
	
	/**
	 * 插入数据
     * 
     * @param request 插入对象
     * @return 返回插入的对象
	 */
	CommonResponse insert(${className}Request request);
	
	/**
	 * 根据主键删除数据
     * 
     * @param id 主键
	 */
	CommonResponse delete(<#list table.compositeIdColumns as column>${column.javaType} ${column.columnNameLower} <#if column_has_next> , </#if></#list>);
}


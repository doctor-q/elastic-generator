<#include "/macro.include"/>
<#assign className = table.className>
<#assign remarks = table.remarks>
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${basepackage}.controller.response.CommonResponse;
import ${basepackage}.controller.response.PageResponse;
import com.google.common.base.Preconditions;
import ${basepackage}.controller.request.${className}Request;
import ${basepackage}.dao.mapper.${className}Mapper;
import ${basepackage}.dao.model.${className};
import ${basepackage}.service.${className}Service;

<@classComment value="service实现接口"/>
@Service
public class ${className}ServiceImpl implements ${className}Service {
	/**
     * ${remarks} Mapper
     */
    @Autowired
    private ${className}Mapper ${classNameLower}Mapper;
	
	@Override
	@Transactional(readOnly = true)
	public CommonResponse get(<#list table.compositeIdColumns as column>${column.javaType} ${column.columnNameLower} <#if column_has_next> , </#if></#list>){
		<#list table.compositeIdColumns as column>
		Preconditions.checkNotNull(${column.columnNameLower});
		</#list>

		${className} record = ${classNameLower}Mapper.selectByPrimaryKey(<#list table.compositeIdColumns as column>${column.columnNameLower} <#if column_has_next> , </#if></#list>);
		return CommonResponse.successResponse(record);
	}
	
	@Override
	@Transactional(readOnly = true)
	public CommonResponse getPage(${className}Request request){
		${className} record = request.to${className}();
		Integer count = ${classNameLower}Mapper.selectCount(record);
		List<${className}> list = ${classNameLower}Mapper.selectPage(record, request.toRowBounds());
		return CommonResponse.successResponse(new PageResponse<>(request, count, list));
	}
	
	@Override
	public CommonResponse update(${className}Request request) {
		${className} record = request.to${className}();
		${classNameLower}Mapper.updateSelective(record);
		return CommonResponse.successResponse();
	}
	
	@Override
	public CommonResponse insert(${className}Request request){
		${className} record = request.to${className}();
		${classNameLower}Mapper.insertSelective(record);
		return CommonResponse.successResponse();
	}
	
	@Override
	public CommonResponse delete(<#list table.compositeIdColumns as column>${column.javaType} ${column.columnNameLower} <#if column_has_next> , </#if></#list>) {
		<#list table.compositeIdColumns as column>
		Preconditions.checkNotNull(${column.columnNameLower});
		</#list>

		${classNameLower}Mapper.deleteByPrimaryKey(<#list table.compositeIdColumns as column>${column.columnNameLower} <#if column_has_next> , </#if></#list>);
		return CommonResponse.successResponse();
	}
}

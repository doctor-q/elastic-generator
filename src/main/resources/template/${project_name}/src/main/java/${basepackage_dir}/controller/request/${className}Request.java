<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.controller.request;

<#list table.columns as column>
<#assign hasDateType = false>
<#if column.isDateTimeColumn>
    <#assign hasDateType = true>
</#if>
</#list>
<#if hasDateType>
import java.util.Date;
</#if>
import com.alibaba.fastjson.JSON;
import ${basepackage}.controller.request.PageRequest;
import ${basepackage}.dao.model.${className};
import org.springframework.beans.BeanUtils;

<@classComment value="请求体"/>
public class ${className}Request extends PageRequest implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

<#list table.columns as column>
	/**
	 * <#if (column.pk==true)>主键列</#if>
	 * ${column.columnAlias!}
	 */
	private ${column.javaType} ${column.columnNameLower};
</#list>

<@generateJavaColumns/>

	public String toString() {
		return JSON.toJSONString(this);
	}

	public ${className} to${className}() {
		${className} ${classNameLower} = new ${className}();
		BeanUtils.copyProperties(this, ${classNameLower});
		return ${classNameLower};
	}
	
}

<#macro generateJavaColumns>
	<#list table.columns as column>
	/**
     * 获取${column.columnAlias!} 
     */
	public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}
	/**
     * 设置${column.columnAlias!} 
     */
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	</#list>
</#macro>

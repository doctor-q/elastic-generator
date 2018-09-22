<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.dao.model;

<#list table.columns as column>
<#assign hasDateType = false>
<#if column.isDateTimeColumn>
    <#assign hasDateType = true>
</#if>
</#list>
<#if hasDateType>
import java.util.Date;
</#if>

<@classComment value="模型类"/>
public class ${className} {
	<#list table.columns as column>
	/**
     * <#if (column.pk==true)>主键列</#if>
     * ${column.columnAlias!} 
     */
	private ${column.javaType} ${column.columnNameLower};
	</#list>
	
	<@generateConstructor className/>
	<@generateJavaColumns/>

}

<#macro generateJavaColumns>
	<#list table.columns as column>
	
	public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}
	
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	</#list>
</#macro>

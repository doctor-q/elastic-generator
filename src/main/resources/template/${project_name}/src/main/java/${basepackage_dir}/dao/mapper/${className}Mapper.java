<#assign className = table.className>   
<#assign remarks = table.remarks> 
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.dao.mapper;

import java.util.List;

import ${basepackage}.dao.model.${className};
import org.apache.ibatis.session.RowBounds;

<@classComment value="Mapper映射文件"/>
public interface ${className}Mapper {
	/**
	 * 根据主键查询
     * 
     * @param id 主键
     * @return ${remarks}
	 */
	${className} selectByPrimaryKey(<#list table.compositeIdColumns as column>${column.javaType} ${column.columnNameLower} <#if column_has_next> , </#if></#list>);

	/**
	 * 根据查询条件查询数据数量
	 *
	 * @param record 查询对象
	 * @return 数据数量
	 */
	int selectCount(${className} record);

	/**
	 * 根据查询条件查询数据
     * 
     * @param record 查询对象
     * @return ${remarks}集合
	 */
	List<${className}> selectPage(${className} record, RowBounds rowBounds);
	
	/**
	 * 根据主键查询数据
     * 
     * @param record 插入对象
	 */
	int insertSelective(${className} record);
	
	/**
	 * 根据主键更新数据
     * 
     * @param record 更新对象
     * @return 操作影响行数
	 */
	int updateSelective(${className} record);
	
	/**
	 * 删除数据
     * 
     * @param id 主键
     * @return 操作影响行数
	 */
	int deleteByPrimaryKey(<#list table.compositeIdColumns as column>${column.javaType} ${column.columnNameLower} <#if column_has_next> , </#if></#list>);
	
}

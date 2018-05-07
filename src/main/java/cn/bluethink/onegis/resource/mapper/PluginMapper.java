package cn.bluethink.onegis.resource.mapper;

import java.util.List;

import cn.bluethink.onegis.resource.model.Plugin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PluginMapper {

	/**
	 * 新增插件
	 * @param plugin
	 * @return
	 * @throws Exception
	 */
	int insert(Plugin plugin) throws Exception;
	
	/**
	 * 编辑插件信息
	 * @param plugin
	 * @return
	 * @throws Exception
	 */
	int update(Plugin plugin) throws Exception;
	
	/**
	 * 根据Id获取用户信息
	 * @param Id
	 * @return
	 * @throws Exception
	 */
	Plugin getById(@Param("id") long id) throws Exception;
	
	/**
	 * 根据Id进行逻辑删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int remove(@Param("id") long id) throws Exception;
	
	/**
	 * 根据资源名称/标签模糊查询
	 * @param name
	 * @param tags
	 * @return
	 * @throws Exception
	 */
	List<Plugin> getPluginList(@Param("name") String name, 
			@Param("tags") String tags) throws Exception;
	
}

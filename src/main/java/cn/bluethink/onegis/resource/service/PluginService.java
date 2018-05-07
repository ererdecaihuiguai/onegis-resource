package cn.bluethink.onegis.resource.service;

import com.github.pagehelper.PageInfo;

import cn.bluethink.onegis.resource.model.Plugin;

public interface PluginService {

	/**
	 * 保存（新增/编辑）插件资源
	 * @param plugin
	 * @return
	 * @throws Exception
	 */
	boolean save(Plugin plugin) throws Exception;
	
	/**
	 * 根据资源Id查询资源信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Plugin getById(long id) throws Exception;
	
	/**
	 * 根据资源Id进行逻辑删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean delete(long id) throws Exception;
	
	/**
	 * 分页查询资源列表
	 * @param name
	 * @param tags
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	PageInfo<Plugin> getForPage(String name, String tags,
			int pageNum, Integer pageSize) throws Exception;

}

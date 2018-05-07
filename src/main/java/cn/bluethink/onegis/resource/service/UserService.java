package cn.bluethink.onegis.resource.service;

import com.github.pagehelper.PageInfo;

import cn.bluethink.onegis.resource.model.User;

public interface UserService {

	/**
	 * 保存（新增/编辑）用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean save(User user) throws Exception;
	
	/**
	 * 根据用户Id查询用户信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	User getById(long id) throws Exception;
	
	/**
	 * 根据用户Id进行逻辑删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean delete(long id) throws Exception;
	
	/**
	 * 分页查询用户信息
	 * @param userName
	 * @param nickName
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	PageInfo<User> getForPage(String userName, String nickName,
			int pageNum, int pageSize) throws Exception;

}

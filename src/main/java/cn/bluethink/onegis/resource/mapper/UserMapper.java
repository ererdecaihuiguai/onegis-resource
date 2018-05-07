package cn.bluethink.onegis.resource.mapper;


import java.util.List;

import cn.bluethink.onegis.resource.model.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

	/**
	 * 新增用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int insert(User user) throws Exception;
	
	/**
	 * 编辑用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int update(User user) throws Exception;
	
	/**
	 * 根据Id获取用户信息
	 * @param Id
	 * @return
	 * @throws Exception
	 */
	User getById(@Param("id") long Id) throws Exception;
	
	/**
	 * 根据Id进行逻辑删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int remove(@Param("id") long id) throws Exception;
	
	/**
	 * 根据用户账号/昵称进行模糊查询
	 * @param userName
	 * @param nickName
	 * @return
	 * @throws Exception
	 */
	List<User> getUserList(@Param("userName") String userName,
			@Param("nickName") String nickName) throws Exception;

}

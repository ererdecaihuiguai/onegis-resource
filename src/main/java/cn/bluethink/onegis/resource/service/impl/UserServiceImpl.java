package cn.bluethink.onegis.resource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.bluethink.onegis.resource.mapper.UserMapper;
import cn.bluethink.onegis.resource.model.User;
import cn.bluethink.onegis.resource.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public boolean save(User user) throws Exception {
		int flag = 0;
		if(user != null) {
			if(user.getId() > 0){
				flag = userMapper.update(user);
			} else {
				flag= userMapper.insert(user);
			}
		}
		return flag > 0;
	}

	@Override
	public User getById(long id) throws Exception {
		return userMapper.getById(id);
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public boolean delete(long id) throws Exception {
		return userMapper.remove(id) > 0;
	}

	@Override
	public PageInfo<User> getForPage(String userName, String nickName, 
			int pageNum, int pageSize) throws Exception {
		PageInfo<User> pageInfo = PageHelper.startPage(pageNum, pageSize)
				.doSelectPageInfo(()->{
					try {
						userMapper.getUserList(userName, nickName);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
		return pageInfo;
	}

}

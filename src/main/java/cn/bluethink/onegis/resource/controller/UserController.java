package cn.bluethink.onegis.resource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import cn.bluethink.onegis.resource.model.User;
import cn.bluethink.onegis.resource.service.UserService;
import cn.bluethink.onegis.resource.utils.BtResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags="User API", description = "用户管理")
@RequestMapping("/api/user")
@CrossOrigin
@RestController
public class UserController {

	@Autowired
	private UserService userService;

    @ApiOperation("保存（新增/编辑）用户信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userName", value = "用户账号", dataType = "String", paramType = "form"),
        @ApiImplicitParam(name = "nickName", value = "用户昵称", dataType = "String", paramType = "form"),
        @ApiImplicitParam(name = "avatar", value = "用户头像", dataType = "String", paramType = "form"),
        @ApiImplicitParam(name = "email", value = "电子邮箱", dataType = "String", paramType = "form"),
        @ApiImplicitParam(name = "signature", value = "个性签名", dataType = "String", paramType = "form")
    })
	@PostMapping("/save")
	public BtResult save(User user) throws Exception {
		if(userService.save(user)) {
			long userId = user.getId();
			return BtResult.OK(userId);
		}
		return BtResult.ERROR();
	}
	
    @ApiOperation("根据Id删除用户")
    @ApiImplicitParam(name = "id", value = "用户Id", dataType = "long", paramType = "form")
	@PostMapping("/delete")
	public BtResult delete(long id) throws Exception {
		if(userService.delete(id)) {
			return BtResult.OK();
		}
		return BtResult.ERROR();
	}
    
    @ApiOperation("根据Id获取用户信息")
    @ApiImplicitParam(name = "id", value = "用户Id", dataType = "long", paramType = "query")
	@GetMapping("/getById")
	public BtResult getById(long id) throws Exception {
    	User user = userService.getById(id);
    	return BtResult.OK(user);
	}
    
    @ApiOperation("分页查询用户列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userName", value = "用户账号", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "nickName", value = "用户昵称", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示数量", dataType = "Integer", paramType = "query")
    })
	@GetMapping("/getForPage")
	public BtResult getForPage(@RequestParam(required=false) String userName,
			@RequestParam(required=false) String nickName,
			@RequestParam(required=false, defaultValue="1") int pageNum,
			@RequestParam(required=false, defaultValue="20") int pageSize) throws Exception {
    	PageInfo<User> pageInfo = userService.getForPage(userName, nickName, pageNum, pageSize);
    	return BtResult.OK(pageInfo);
	}

}

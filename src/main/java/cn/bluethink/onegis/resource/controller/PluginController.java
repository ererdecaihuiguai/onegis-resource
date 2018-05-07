package cn.bluethink.onegis.resource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import cn.bluethink.onegis.resource.model.Plugin;
import cn.bluethink.onegis.resource.service.PluginService;
import cn.bluethink.onegis.resource.utils.BtResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags="Plugin API", description = "插件管理")
@RequestMapping("/api/plugin")
@CrossOrigin
@RestController
public class PluginController {

	@Autowired
	private PluginService pluginService;

    @ApiOperation("保存（新增/编辑）插件资源")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "关联用户ID", dataType = "Long", paramType = "form"),
        @ApiImplicitParam(name = "name", value = "资源名称", dataType = "String", paramType = "form"),
        @ApiImplicitParam(name = "icon", value = "资源图片", dataType = "String", paramType = "form"),
        @ApiImplicitParam(name = "version", value = "插件版本", dataType = "String", paramType = "form"),
        @ApiImplicitParam(name = "tags", value = "资源标签", dataType = "String", paramType = "form"),
        @ApiImplicitParam(name = "description", value = "资源描述", dataType = "String", paramType = "form")
	})
	@PostMapping("/save")
	public BtResult save(Plugin plugin) throws Exception {
		if(pluginService.save(plugin)) {
			long pluginId = plugin.getId();
			return BtResult.OK(pluginId);
		}
		return BtResult.ERROR();
	}

    @ApiOperation("根据Id删除资源")
    @ApiImplicitParam(name = "id", value = "资源Id", dataType = "long", paramType = "form")
	@PostMapping("/delete")
	public BtResult delete(long id) throws Exception {
		if(pluginService.delete(id)) {
			return BtResult.OK();
		}
		return BtResult.ERROR();
	}

    @ApiOperation("根据Id获取插件信息")
    @ApiImplicitParam(name = "id", value = "资源Id", dataType = "long", paramType = "query")
	@GetMapping("/getById")
	public BtResult getById(long id) throws Exception {
    	Plugin plugin = pluginService.getById(id);
    	return BtResult.OK(plugin);
	}

    @ApiOperation("分页查询资源列表")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "name", value = "资源名称", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "tags", value = "资源标签", dataType = "String", paramType = "query"),
    	@ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "integer", paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示数量", dataType = "integer", paramType = "query")
    })
	@GetMapping("/getForPage")
	public BtResult getForPage(@RequestParam(required=false) String name,
			@RequestParam(required=false) String tags,
			@RequestParam(required=false, defaultValue="1") int pageNum,
			@RequestParam(required=false, defaultValue="20") int pageSize) throws Exception {
		PageInfo<Plugin> pageInfo = pluginService.getForPage(name, tags, pageNum, pageSize);
		return BtResult.OK(pageInfo);
	}

}

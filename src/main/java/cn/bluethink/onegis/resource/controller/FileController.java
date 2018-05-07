package cn.bluethink.onegis.resource.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.bluethink.onegis.resource.utils.BtResult;
import cn.bluethink.onegis.resource.utils.FileType;
import cn.bluethink.onegis.resource.utils.FileUtil;
import io.swagger.annotations.Api;

@Api(tags="File API", description = "文件管理")
@RequestMapping("/api/file")
@CrossOrigin
@RestController
public class FileController {
	
	@Value("${onegis.avatar-path}")
	private String avatarPath;
	
	@Value("${onegis.icon-path}")
	private String iconPath;
	
	@Value("${onegis.plugin-path}")
	private String pluginPath;
	
	private static final String CATEGORY_AVATAR = "avatar";
	
	private static final String CATEGORY_ICON = "icon";
	
	private static final String CATEGORY_PLUGIN = "plugin";
	
	@PostMapping("/{category}")
	public BtResult upload(@PathVariable("category") String category,
			@RequestParam("file") MultipartFile file) throws Exception{
		String fileName = null;
		if((CATEGORY_AVATAR).equals(category)){
			fileName = FileUtil.uploadFile(file, FileType.PICTURE_FILE, avatarPath);
			System.out.println("保存成功");
		}else if((CATEGORY_ICON).equals(category)){
			fileName = FileUtil.uploadFile(file, FileType.PICTURE_FILE, iconPath);
			System.out.println("保存成功");
		}else if((CATEGORY_PLUGIN).equals(category)){
			fileName = FileUtil.uploadFile(file, FileType.COMPRESSED_FILE, pluginPath);
			System.out.println("保存成功");
			System.out.println("哈哈哈，保存成功");
		}else{
			fileName = FileUtil.uploadFile(file, FileType.COMPRESSED_FILE, pluginPath);
			System.out.println("保存成功");
		}
		return BtResult.OK(fileName);
	}
	
	@GetMapping("/{category}/{fileName:.+}")
	public void download(@PathVariable("category") String category,
			@PathVariable("fileName") String fileName,
			HttpServletResponse response) throws Exception{
		if((CATEGORY_AVATAR).equals(category)){
			String absolutePath = avatarPath + "/" + fileName;
			FileUtil.load(absolutePath, response);
		}else if((CATEGORY_ICON).equals(category)){
			String absolutePath = iconPath + "/" + fileName;
			FileUtil.load(absolutePath, response);
		}else if((CATEGORY_PLUGIN).equals(category)){
			String absolutePath = pluginPath + "/" + fileName;
			FileUtil.download(absolutePath, response);
		}
	}
}

package cn.bluethink.onegis.resource.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.UUID;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	/**
	 * 上传文件
	 * @param mFile
	 * @param fileType
	 * @param parentDir
	 * @return
	 * @throws Exception
	 */
	public static String uploadFile(MultipartFile mFile, FileType fileType, String parentDir) throws Exception{
		String absolutePath = null;
		if(mFile != null && !mFile.isEmpty() 
				&& parentDir != null && !("").equals(parentDir)){
			//判断文件目录是否存在
			File saveDirectory = new File(parentDir);
			if(!saveDirectory.exists()){
				saveDirectory.mkdirs();
			}
			
			String originalName = mFile.getOriginalFilename();
			String suffix = originalName.substring(originalName.lastIndexOf(".")).toLowerCase();
			
			//判断文件类型
			if(!fileType.getSupportedType().contains(suffix)){
				throw new RuntimeException(fileType.getMessage());
			}
				
			//使用UUID生成新的文件名，防止文件名重复
			String saveName = UUID.randomUUID().toString().replace("-", "") + suffix;
			absolutePath = parentDir + "/" + saveName;
			//保存文件
			mFile.transferTo(new File(absolutePath));
			return saveName;
		}
		return null;
	}
	
	/**
	 * 文件下载
	 * @param absolutePath
	 * @param response
	 * @throws Exception
	 */
	public static void download(String absolutePath, HttpServletResponse response) throws Exception {
		download(absolutePath, null, response);
	}
	
	/**
	 * 文件下载
	 * @param absolutePath
	 * @param originalName
	 * @param response
	 * @throws Exception
	 */
	public static void download(String absolutePath, String originalName, HttpServletResponse response) throws Exception {
		if(absolutePath!=null && !("").equals(absolutePath)){
			File file = new File(absolutePath);
			if(file.isFile() && file.exists()){
				String fileName = absolutePath.substring(absolutePath.lastIndexOf("/") + 1);
				response.setContentType(new MimetypesFileTypeMap().getContentType(new File(fileName)));
				if(originalName!=null && !("").equals(originalName)){
					fileName = originalName;
				}
				response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName, "UTF-8"));
				write(new FileInputStream(file), response.getOutputStream());
			} else {
				throw new RuntimeException("文件不存在,下载失败！");
			}
		}
	}

	/**
	 * 加载图片文件
	 * @param absolutePath
	 * @param response
	 * @throws Exception
	 */
	public static void load(String absolutePath, HttpServletResponse response) throws Exception {
		if(absolutePath!=null && !("").equals(absolutePath)){
			File file = new File(absolutePath);
			if(file.isFile() && file.exists()){
				try {
					write(new FileInputStream(file), response.getOutputStream());
				} catch (FileNotFoundException e) {
					throw new RuntimeException("该文件不存在！");
				}
			}
		}
	}
	
	/**
	 * 写入文件
	 * @param in
	 * @param out
	 * @throws Exception
	 */
	private static void write(InputStream in, OutputStream out) throws IOException{
		try {
			byte[] buffer = new byte[1024];
			int len = -1;
			while((len = in.read(buffer))!=-1){
				out.write(buffer, 0, len);
			}
			out.flush();
		} finally{
			if(in!=null){
				in.close();
			}
			if(out!=null){
				out.close();
			}
		}
	}

}


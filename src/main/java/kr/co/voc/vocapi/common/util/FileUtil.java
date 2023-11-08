package kr.co.rmsoft.tms.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import kr.co.rmsoft.tms.common.model.CommonFile;

@Component
public class CommonFileUtil {
//	private static final Logger logger = LoggerFactory.getLogger(CommonFileUtil.class);
	
	public static String tempPath;

	@Value("${spring.servlet.multipart.location}")
    public void setKey(String value) {
    	tempPath = value;
    }
	
	/**
	 * 첨부로 등록된 파일을 서버에 업로드한다.
	 * parameter 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	
	public static List<CommonFile> attachFile(List<MultipartFile> files) throws Exception {
		CommonFile commonFile = null;
		List<CommonFile> commonFileList = null;
		
		File destdir = new File(tempPath);
        if(!destdir.exists()){
            destdir.mkdirs();
        }
        
		commonFileList = new ArrayList<CommonFile>();

		for (MultipartFile file : files) {
			File tmp = new File(tempPath + UUID.randomUUID().toString());
			file.transferTo(tmp);			
			
			commonFile = new CommonFile();
			String fileNm = file.getOriginalFilename();
			commonFile.setFileOriginNm(fileNm);
			commonFile.setFileExtn(fileNm.substring(fileNm.lastIndexOf(".") + 1));
			commonFile.setFileSize(new Long(file.getSize()).intValue());
			commonFile.setFilePath(tmp.getPath());
			commonFileList.add(commonFile);
		}
		
		return commonFileList;
	}
	
	/**
	 * 등록된 파일을 삭제한다.
	 * parameter 
	 * @param file
	 * @return
	 * @throws Exception
	 */
    public static void deleteFileData(CommonFile file) {
    	 new File(file.getFilePath()).delete();
    }
	
	
	/**
	 * 첨부로 등록된 파일을 다운로드한다.
	 * parameter 
	 * @param file
	 * @return
	 * @throws Exception
	 */
    public static Resource loadFileAsResource(String fileName) throws Exception {
    	Resource resource = null;
        try {
            Path filePath = Paths.get(fileName).normalize();
            resource = new UrlResource(filePath.toUri());
            
            if(resource.exists()) {
                //return resource;
            }else {
            	throw new FileNotFoundException(fileName);
            }
        }catch(MalformedURLException e) {
        	throw new FileNotFoundException(fileName);
        }
		return resource;
    }
    
	public static boolean doFileDownload(HttpServletResponse response, CommonFile file) {

		try {

			//파일을 다운 받아 클라이언트컴에 파일이름 생성시 한글 깨짐 방지
			String originalFileName = new String(file.getFileOriginNm().getBytes("euc-kr"),"ISO-8859-1");//8859_1

			File f = new File(file.getFilePath());

			if(!f.exists())
				return false;

			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", 
					"attachment;fileName=" + originalFileName);

			BufferedInputStream bis = 
					new BufferedInputStream(new FileInputStream(f));

			OutputStream out = response.getOutputStream();

			int n;
			byte[] data = new byte[4096];
			while((n=bis.read(data, 0, 4096))!=-1) {
				out.write(data,0,n);
			}

			out.flush();
			out.close();
			bis.close();

		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}

		return true;	

	}

}

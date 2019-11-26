package bts.e.p003.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import bts.e.p003.Service.E_P003Service;
import bts.e.p003.VO.E_P003VO;
import bts.e.p003.VO.E_P003VO_2;

@Controller("e_p003Controller")
@RequestMapping("/accompany2")
public class E_P003ControllerImpl implements E_P003Controller{
	@Autowired
	E_P003Service e_p003Service;
	@Autowired
	E_P003VO e_p003VO;
	@Autowired
	E_P003VO_2 e_p003VO_2;
	
	private static final String imageUrl = "C:\\Users\\bit\\git\\bts\\bts\\src\\main\\webapp\\resources\\image\\accompany";
	private static final String metaUrl = "D:\\project\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\bts\\resources\\image\\board";
	private static final String mappingUrl = "/resources/image/accompany";
	
	@Override
	@RequestMapping(value="/accInsert", method= {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity accInsert(@ModelAttribute("e_p003VO")E_P003VO e_p003VO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		ObjectMapper mapper = new ObjectMapper();
		ArrayList imageList = mapper.readValue(request.getParameter("imageList"), ArrayList.class);
		String member_id = (String) session.getAttribute("member_id");
		e_p003VO.setMember_id(member_id);
		System.out.println("accInsert:" + member_id);
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			for(int i=0; i<imageList.size(); i++) {
				String srcUrl = (String) imageList.get(i);
				srcUrl = srcUrl.substring(srcUrl.indexOf(mappingUrl)+mappingUrl.length());
				srcUrl = srcUrl.replace("/", "\\");
				
				File downloadFile = new File(metaUrl+"\\"+srcUrl);
				File uploadFile = new File(imageUrl+"\\"+srcUrl);
				
				if(!uploadFile.getParentFile().exists()) {
					uploadFile.getParentFile().mkdirs();
				}
				System.out.println(downloadFile.renameTo(uploadFile));
			}
			ArrayList tagList = mapper.readValue(request.getParameter("tagList"),ArrayList.class);
			e_p003VO.setContent(request.getParameter("editor"));
			e_p003Service.insertAcc(e_p003VO);		
			List<E_P003VO_2> tagInsert = new ArrayList<>();
			for(int i=0; i<tagList.size(); i++) {
				e_p003VO_2.setArticle_no(e_p003VO.getArticle_no());
				e_p003VO_2.setTag_name((String) tagList.get(i));
				tagInsert.add(e_p003VO_2);
			}
			e_p003Service.insertTag(tagInsert);
			message = "<script>";
			message += "alert('글작성을 마쳤습니다. 목록으로 이동합니다.');";
			message += "location.href='"+request.getContextPath() +"/accompany/accMain';";
			message += "</script>";
		}catch(Exception e) {
			message = "<script>";
			message += "alert('다시 시도해 주세요.');";
			message += "history.go(-1);";
			message += "</script>";
			e.printStackTrace();
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}
	
	@ResponseBody
	@RequestMapping(value="/image" ,method={RequestMethod.POST,RequestMethod.GET}, produces="application/json;charset=UTF-8")
	public String upload(HttpServletRequest request, HttpServletResponse response,@RequestParam MultipartFile upload) throws Exception {
		
		OutputStream out = null;	
		try {
			String fileName = upload.getOriginalFilename();
			byte[] bytes = upload.getBytes();
			String saveDate=new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
			String randID = UUID.randomUUID().toString();
			
			String metaPath=metaUrl+"\\"+saveDate+"\\"+randID+"_"+fileName;
			File metaFile = new File(metaPath);
			
			if(!metaFile.getParentFile().exists()) {
				metaFile.getParentFile().mkdirs();
			}
			
			out = new FileOutputStream(metaFile);
			out.write(bytes);
			
			String fileUrl = request.getContextPath()+mappingUrl+"/"+saveDate+"/"+randID+"_"+fileName;
			JSONObject obj = new JSONObject();
			obj.put("uploaded", 1);
			obj.put("fileName",fileName);
			obj.put("url",fileUrl);
			return obj.toJSONString();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
	}
}

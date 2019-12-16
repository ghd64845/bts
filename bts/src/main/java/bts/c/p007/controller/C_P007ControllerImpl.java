package bts.c.p007.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bts.c.p007.VO.C_P007VO;
import bts.c.p007.service.C_P007Service;
import bts.e.p001.VO.PagingVO;

@Controller("c_p007Controller")
@RequestMapping("/my/accompany")
public class C_P007ControllerImpl implements C_P007Controller{
	@Autowired
	C_P007Service c_p007Service;
	@Autowired
	C_P007VO c_p007VO;

	@Override
	@RequestMapping(value="/accList", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView myAccList(PagingVO pagingVO
			,@RequestParam(value="nowPage", required=false) String nowPage
			,@RequestParam(value="cntPerPage", required=false) String cntPerPage
			,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/c/p007/d001");
		HttpSession session = request.getSession();
		String member_id = (String) session.getAttribute("member_id");
		System.out.println("member_idddddddddddddddd:" + member_id);
		
		int total = c_p007Service.listCount(member_id);
		
		if(nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		}else if(nowPage == null) {
			nowPage = "1";
		}else if(cntPerPage == null) {
			cntPerPage = "5";
		}
		pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		c_p007VO.setMember_id(member_id);
		pagingVO.setMember_id(member_id);
		List<C_P007VO> list = c_p007Service.myAccList(pagingVO);
		System.out.println("mypagelistttttttttttt:" + list);
		mav.addObject("paging",pagingVO);
		mav.addObject("accList", list);
		return mav;
	}
	
	
}
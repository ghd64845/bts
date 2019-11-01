package bts.z.p000;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("z_p000")
@RequestMapping(value="/main")
public class P000ControllerImpl implements p000Controller{

		@Override
		@RequestMapping(value="/main" , method= {RequestMethod.POST , RequestMethod.GET})
		public ModelAndView Z_P000_D001(HttpServletRequest request, HttpServletResponse response) throws Exception{
			request.setCharacterEncoding("utf-8");
			
			ModelAndView mav = new ModelAndView("z/p000/d001");
			return mav;
		}
}

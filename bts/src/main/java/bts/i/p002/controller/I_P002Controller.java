package bts.i.p002.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public interface I_P002Controller {
	public ModelAndView I_P002_D001(HttpServletRequest request, HttpServletResponse response)throws Exception;
	public @ResponseBody ModelAndView planInsert(@RequestParam Map<String, String> result , HttpServletRequest request, HttpServletResponse response)throws Exception;
}

package bts.a.p002.vo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("a_p002VO_1")
@Scope("prototype")
public class A_P002VO_1 {
	private String pnish_cd;
	private String name;
	private int day_cnt;
	
	public String getPnish_cd() {
		return pnish_cd;
	}
	public void setPnish_cd(String pnish_cd) {
		this.pnish_cd = pnish_cd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDay_cnt() {
		return day_cnt;
	}
	public void setDay_cnt(int day_cnt) {
		this.day_cnt = day_cnt;
	}
	
}

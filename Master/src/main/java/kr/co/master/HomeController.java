package kr.co.master;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.ProtocolException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import kr.co.master.dao.Idao;
import kr.co.master.dto.Bookmark;
import kr.co.master.dto.List;
import kr.co.master.dto.Member;
import kr.co.master.dto.Notice;
import kr.co.master.dto.Request;
import kr.co.master.dto.Station;

@Controller
public class HomeController {
	SqlSession sqlSession;
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@RequestMapping("/")
	public String login() {
		
		return "login";
	}
	
	@RequestMapping("join.do")
	public String join() {
		
		return "join";
	}
	
	@RequestMapping("idCheck.do")
	public String idCheck(HttpServletRequest request,Model model) {
		String use_id = request.getParameter("use_id");
		String id = request.getParameter("id");
		
		if (id == null) {
			id = "";
		}
		
		model.addAttribute("use_id",use_id);
		model.addAttribute("id",id);
		
		return "idCheck";
	}
	
	@RequestMapping("id_check_ok.do")
	public String idCheckOk(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String id = request.getParameter("id");
		
		Idao idao = sqlSession.getMapper(Idao.class);
		Integer count = idao.stationCount(id);
		
		if (count > 0) {
			redirectAttributes.addAttribute("use_id", 0);
		} else {
			redirectAttributes.addAttribute("id", id);
			redirectAttributes.addAttribute("use_id", 1);
		}
		
		return "redirect:idCheck.do";
	}
	
	@RequestMapping("join_ok.do")
	public String join_ok(HttpServletRequest request,Model model) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		
		Idao idao = sqlSession.getMapper(Idao.class);
		idao.insertStation(id, pw, name, phone);
		
		return "redirect:/";
	}
	
	@RequestMapping("login_ok.do")
	public String login_ok(HttpServletRequest request) {
		String st_id = request.getParameter("st_id");
		String st_pw = request.getParameter("st_pw");
		
		Idao idao = sqlSession.getMapper(Idao.class);
		Integer logck = idao.login(st_id, st_pw);
		
		if(logck > 0) {
			HttpSession httpSession = request.getSession();
			Station st_member = idao.getStationInfo(st_id, st_pw);
			httpSession.setAttribute("st_member", st_member);
			
			return "redirect:request.do";
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("request.do")
	public String request(HttpServletRequest request,Model model) {
		Idao idao = sqlSession.getMapper(Idao.class);
		HttpSession httpSession = request.getSession();
		Station st_member = (Station)httpSession.getAttribute("st_member");
		
		ArrayList<List> requests = idao.getList(st_member.getSt_id());
		
		
		model.addAttribute("item", requests);
		
		return "request";
	}
	
	@RequestMapping("refuse.do")
	public String refuse(HttpServletRequest request,Model model) {
		Idao idao = sqlSession.getMapper(Idao.class);
		HttpSession httpSession = request.getSession();
		Station st_member = (Station)httpSession.getAttribute("st_member");
		
		ArrayList<List> refuse = idao.getReList(st_member.getSt_id());
		
		
		model.addAttribute("item", refuse);
		
		return "refuse";
	}
	
	@RequestMapping("logout.do")
	public String logout(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute("st_member");
		
		return "redirect:/";
	}
	
	@RequestMapping("approval.do")
	public String approval(HttpServletRequest request, Model model) {
		Idao idao = sqlSession.getMapper(Idao.class);
		HttpSession httpSession = request.getSession();
		Station st_member = (Station)httpSession.getAttribute("st_member");
		
		ArrayList<List> approval = idao.getAppList(st_member.getSt_id());
		
		
		model.addAttribute("item", approval);
		
		return "approval";
	}
	
	@RequestMapping("booking.do")
	public String booking(HttpServletRequest request, Model model) {
		HttpSession httpSession = request.getSession();
		Station st_member = (Station)httpSession.getAttribute("st_member");
		
		Idao idao = sqlSession.getMapper(Idao.class);
		ArrayList<List> booking = idao.getBooking(st_member.getSt_id());
		
		model.addAttribute("item", booking);
		
		
		return "booking";
	}
	
	@RequestMapping("post.do")
	public String post(HttpServletRequest request,Model model) {
		String id = request.getParameter("id");
		System.out.println(id);
		Idao idao = sqlSession.getMapper(Idao.class);
		List requests = idao.getPost(Long.parseLong(id));
		
		model.addAttribute("item", requests);
		
		return "postpu";
	}
	
	@RequestMapping("nextappr.do")
	public String nextappr(HttpServletRequest request, Model model, HttpSession session) {
		String id = request.getParameter("id");
		
		Idao idao = sqlSession.getMapper(Idao.class);
		idao.updateList(Long.parseLong(id));
		
		List list = idao.chMyList(Long.parseLong(id));
		String title = list.getMember().getMem_name() + "님";
		String body = null;
		if(list.getRequest().getLi_kind() == 0) {
			body = list.getRequest().getLi_station()+"역 출발이 승인되었습니다.";
		}else {
			body = list.getRequest().getLi_station()+"역 도착이 승인되었습니다.";
		}
		String sendKey = list.getMember().getMem_key();
		
		final String apiKey = "AAAA34bus-Y:APA91bGntKv50NXJSnMZoJ07R-Ti0UorR6vZ-5FzRG1285IYqvtEj3y4SF4P8Aot4fe9u1TChZ2gyzA_mEZKhqXeNT307fAqNNS8WKEaldQXRiG-GK0v5ecreOvEnWvCoMFuzPoml6a3";
        URL url = null;
        HttpURLConnection conn = null;
        
		try {
			url = new URL("https://fcm.googleapis.com/fcm/send");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        conn.setDoOutput(true);
        
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "key=" + apiKey);

        conn.setDoOutput(true);
        
        String userId =(String) request.getSession().getAttribute("ssUserId");
        
        String input = "{\"notification\" : {\"title\" : \""+title+"\", \"body\" : \""+body+"\"}, \"to\":\""+sendKey+"\"}";

        OutputStream os = null;
        int responseCode = 0;
        BufferedReader in = null;
        String inputLine;
        StringBuffer response = new StringBuffer();
        
        try {
        	os = conn.getOutputStream();
			os.write(input.getBytes("UTF-8"));
			os.flush();
			os.close();
			responseCode = conn.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
	        System.out.println("Post parameters : " + input);
	        System.out.println("Response Code : " + responseCode);
	        
	        in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((inputLine = in.readLine()) != null) {
			    response.append(inputLine);
			}
			in.close();
	        
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

        
        // print result
        System.out.println(response.toString());
        
   
        
		return "redirect:approval.do";
	}
	
	@RequestMapping("delappr.do")
	public String delappr(HttpServletRequest request) {
		String id = request.getParameter("id");
		
		Idao idao = sqlSession.getMapper(Idao.class);
		idao.delList(Long.parseLong(id));
		
		return "redirect:approval.do";
	}
	
	@RequestMapping("delappr2.do")
	public String delappr2(HttpServletRequest request) {
		String id = request.getParameter("id");
		
		Idao idao = sqlSession.getMapper(Idao.class);
		idao.delList(Long.parseLong(id));
		
		return "redirect:refuse.do";
	}
	
	
	@RequestMapping("search_r.do")
	public String search_r(HttpServletRequest request ,Model model) {
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String name = request.getParameter("name");
		String kind = request.getParameter("kind");
		Long mo = Long.parseLong(month);
		Long da = Long.parseLong(day);
		Long ki = Long.parseLong(kind);
		
		Idao idao = sqlSession.getMapper(Idao.class);
		HttpSession httpSession = request.getSession();
		Station st_member = (Station)httpSession.getAttribute("st_member");
		ArrayList<List> requests = null;
		
		if(mo == 0 && da == 0 && name.equals("") && ki == 2) {//무 검색
			requests = idao.getList(st_member.getSt_id());
		}else if(mo > 0 && da == 0 && name.equals("") && ki == 2) {//월 검색
			requests = idao.getMoList(st_member.getSt_id(), mo);
		}else if(mo > 0 && da > 0 && name.equals("") && ki == 2) {//월 일 검색
			requests = idao.getMoDaList(st_member.getSt_id(), mo, da);
		}else if(mo > 0 && da > 0 && name.equals("") && ki < 2) {//월 일 내역 검색
			requests = idao.getMoDaKiList(st_member.getSt_id(), mo, da, ki);
		}else if(mo > 0 && da > 0 && !name.equals("") && ki < 2) {//월 일 내역 이름 검색
			requests = idao.getMoDaKiNaList(st_member.getSt_id(), mo, da, ki, name);
		}else if(mo > 0 && da == 0 && !name.equals("") && ki == 2) {//월 이름 검색
			requests = idao.getMoNaList(st_member.getSt_id(), mo, name);
		}else if(mo > 0 && da == 0 && name.equals("") && ki < 2) {//월 내역 검색
			requests = idao.getMoKiList(st_member.getSt_id(), mo, ki);
		}else if(mo > 0 && da > 0 && !name.equals("") && ki == 2) {//월 일 이름 검색
			requests = idao.getMoDaNaList(st_member.getSt_id(), mo, da, name);
		}else if(mo > 0 && da == 0 && !name.equals("") && ki < 2) {//월 내역 이름 검색
			requests = idao.getMoKiNaList(st_member.getSt_id(), mo, ki, name);
		}else if(mo == 0 && da > 0 && name.equals("") && ki == 2) {//일 검색
			requests = idao.getDaList(st_member.getSt_id(), da);
		}else if(mo == 0 && da > 0 && name.equals("") && ki < 2) {//일 내역 검색
			requests = idao.getDaKiList(st_member.getSt_id(), da, ki);
		}else if(mo == 0 && da > 0 && !name.equals("") && ki == 2) {//일 이름 검색
			requests = idao.getDaNaList(st_member.getSt_id(), da, name);
		}else if(mo == 0 && da > 0 && !name.equals("") && ki < 2) {//일 이름 내역 검색
			requests = idao.getDaNaKiList(st_member.getSt_id(), da, name, ki);
		}else if(mo == 0 && da == 0 && !name.equals("") && ki == 2) {//이름 검색
			requests = idao.getNaList(st_member.getSt_id(), name);
		}else if(mo == 0 && da == 0 && !name.equals("") && ki < 2) {//이름 내역 검색
			requests = idao.getNaKiList(st_member.getSt_id(), name, ki);
		}else if(mo == 0 && da == 0 && name.equals("") && ki < 2) {//내역 검색
			requests = idao.getKiList(st_member.getSt_id(), ki);
		}else if(mo > 0 && da > 0 && !name.equals("") && ki < 2) {//월 일 이름 내역 검색
			requests = idao.getAllShTwoList(st_member.getSt_id(), mo, da, name, ki);
		}else {
			requests = idao.getAllShList(st_member.getSt_id(), mo, da, name);
		}
		
		model.addAttribute("item", requests);
		
		return "request";
	}
	
	
	@RequestMapping("search_a.do")
	public String search_a(HttpServletRequest request ,Model model) {
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String name = request.getParameter("name");
		String kind = request.getParameter("kind");
		Long mo = Long.parseLong(month);
		Long da = Long.parseLong(day);
		Long ki = Long.parseLong(kind);
		
		Idao idao = sqlSession.getMapper(Idao.class);
		HttpSession httpSession = request.getSession();
		Station st_member = (Station)httpSession.getAttribute("st_member");
		ArrayList<List> requests = null;
		
		if(mo == 0 && da == 0 && name.equals("") && ki == 2) {//무 검색
			requests = idao.getAppList(st_member.getSt_id());
		}else if(mo > 0 && da == 0 && name.equals("") && ki == 2) {//월 검색
			requests = idao.getAppMoList(st_member.getSt_id(), mo);
		}else if(mo > 0 && da > 0 && name.equals("") && ki == 2) {//월 일 검색
			requests = idao.getAppMoDaList(st_member.getSt_id(), mo, da);
		}else if(mo > 0 && da > 0 && name.equals("") && ki < 2) {//월 일 내역 검색
			requests = idao.getAppMoDaKiList(st_member.getSt_id(), mo, da, ki);
		}else if(mo > 0 && da > 0 && !name.equals("") && ki < 2) {//월 일 내역 이름 검색
			requests = idao.getAppMoDaKiNaList(st_member.getSt_id(), mo, da, ki, name);
		}else if(mo > 0 && da == 0 && !name.equals("") && ki == 2) {//월 이름 검색
			requests = idao.getAppMoNaList(st_member.getSt_id(), mo, name);
		}else if(mo > 0 && da == 0 && name.equals("") && ki < 2) {//월 내역 검색
			requests = idao.getAppMoKiList(st_member.getSt_id(), mo, ki);
		}else if(mo > 0 && da > 0 && !name.equals("") && ki == 2) {//월 일 이름 검색
			requests = idao.getAppMoDaNaList(st_member.getSt_id(), mo, da, name);
		}else if(mo > 0 && da == 0 && !name.equals("") && ki < 2) {//월 내역 이름 검색
			requests = idao.getAppMoKiNaList(st_member.getSt_id(), mo, ki, name);
		}else if(mo == 0 && da > 0 && name.equals("") && ki == 2) {//일 검색
			requests = idao.getAppDaList(st_member.getSt_id(), da);
		}else if(mo == 0 && da > 0 && name.equals("") && ki < 2) {//일 내역 검색
			requests = idao.getAppDaKiList(st_member.getSt_id(), da, ki);
		}else if(mo == 0 && da > 0 && !name.equals("") && ki == 2) {//일 이름 검색
			requests = idao.getAppDaNaList(st_member.getSt_id(), da, name);
		}else if(mo == 0 && da > 0 && !name.equals("") && ki < 2) {//일 이름 내역 검색
			requests = idao.getAppDaNaKiList(st_member.getSt_id(), da, name, ki);
		}else if(mo == 0 && da == 0 && !name.equals("") && ki == 2) {//이름 검색
			requests = idao.getAppNaList(st_member.getSt_id(), name);
		}else if(mo == 0 && da == 0 && !name.equals("") && ki < 2) {//이름 내역 검색
			requests = idao.getAppNaKiList(st_member.getSt_id(), name, ki);
		}else if(mo == 0 && da == 0 && name.equals("") && ki < 2) {//내역 검색
			requests = idao.getAppKiList(st_member.getSt_id(), ki);
		}else if(mo > 0 && da > 0 && !name.equals("") && ki < 2) {//월 일 이름 내역 검색
			requests = idao.getAppAllShTwoList(st_member.getSt_id(), mo, da, name, ki);
		}else {
			requests = idao.getAppAllShList(st_member.getSt_id(), mo, da, name);
		}
		
		model.addAttribute("item", requests);
		
		return "approval";
	}
	
	@RequestMapping("search_re.do")
	public String search_re(HttpServletRequest request ,Model model) {
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String name = request.getParameter("name");
		String kind = request.getParameter("kind");
		Long mo = Long.parseLong(month);
		Long da = Long.parseLong(day);
		Long ki = Long.parseLong(kind);
		
		Idao idao = sqlSession.getMapper(Idao.class);
		HttpSession httpSession = request.getSession();
		Station st_member = (Station)httpSession.getAttribute("st_member");
		ArrayList<List> requests = null;
		
		if(mo == 0 && da == 0 && name.equals("") && ki == 2) {//무 검색
			requests = idao.getReList(st_member.getSt_id());
		}else if(mo > 0 && da == 0 && name.equals("") && ki == 2) {//월 검색
			requests = idao.getReMoList(st_member.getSt_id(), mo);
		}else if(mo > 0 && da > 0 && name.equals("") && ki == 2) {//월 일 검색
			requests = idao.getReMoDaList(st_member.getSt_id(), mo, da);
		}else if(mo > 0 && da > 0 && name.equals("") && ki < 2) {//월 일 내역 검색
			requests = idao.getReMoDaKiList(st_member.getSt_id(), mo, da, ki);
		}else if(mo > 0 && da > 0 && !name.equals("") && ki < 2) {//월 일 내역 이름 검색
			requests = idao.getReMoDaKiNaList(st_member.getSt_id(), mo, da, ki, name);
		}else if(mo > 0 && da == 0 && !name.equals("") && ki == 2) {//월 이름 검색
			requests = idao.getReMoNaList(st_member.getSt_id(), mo, name);
		}else if(mo > 0 && da == 0 && name.equals("") && ki < 2) {//월 내역 검색
			requests = idao.getReMoKiList(st_member.getSt_id(), mo, ki);
		}else if(mo > 0 && da > 0 && !name.equals("") && ki == 2) {//월 일 이름 검색
			requests = idao.getReMoDaNaList(st_member.getSt_id(), mo, da, name);
		}else if(mo > 0 && da == 0 && !name.equals("") && ki < 2) {//월 내역 이름 검색
			requests = idao.getReMoKiNaList(st_member.getSt_id(), mo, ki, name);
		}else if(mo == 0 && da > 0 && name.equals("") && ki == 2) {//일 검색
			requests = idao.getReDaList(st_member.getSt_id(), da);
		}else if(mo == 0 && da > 0 && name.equals("") && ki < 2) {//일 내역 검색
			requests = idao.getReDaKiList(st_member.getSt_id(), da, ki);
		}else if(mo == 0 && da > 0 && !name.equals("") && ki == 2) {//일 이름 검색
			requests = idao.getReDaNaList(st_member.getSt_id(), da, name);
		}else if(mo == 0 && da > 0 && !name.equals("") && ki < 2) {//일 이름 내역 검색
			requests = idao.getReDaNaKiList(st_member.getSt_id(), da, name, ki);
		}else if(mo == 0 && da == 0 && !name.equals("") && ki == 2) {//이름 검색
			requests = idao.getReNaList(st_member.getSt_id(), name);
		}else if(mo == 0 && da == 0 && !name.equals("") && ki < 2) {//이름 내역 검색
			requests = idao.getReNaKiList(st_member.getSt_id(), name, ki);
		}else if(mo == 0 && da == 0 && name.equals("") && ki < 2) {//내역 검색
			requests = idao.getReKiList(st_member.getSt_id(), ki);
		}else if(mo > 0 && da > 0 && !name.equals("") && ki < 2) {//월 일 이름 내역 검색
			requests = idao.getReAllShTwoList(st_member.getSt_id(), mo, da, name, ki);
		}else {
			requests = idao.getReAllShList(st_member.getSt_id(), mo, da, name);
		}
		
		model.addAttribute("item", requests);
		
		return "refuse";
	}
	
	@RequestMapping("sh_year.do")
	public String sh_year(HttpServletRequest request ,Model model) {
		String year = request.getParameter("year");
		Long ye = Long.parseLong(year);
		
		Idao idao = sqlSession.getMapper(Idao.class);
		HttpSession httpSession = request.getSession();
		Station st_member = (Station)httpSession.getAttribute("st_member");
		ArrayList<List> requests = null;
		if(ye == 0) {
			requests = idao.getList(st_member.getSt_id());
		}else {
			requests = idao.getYeList(st_member.getSt_id(), ye);
		}
		
		model.addAttribute("item", requests);
		
		return "request";
	}
	
	@RequestMapping("sh_year_a.do")
	public String sh_year_a(HttpServletRequest request ,Model model) {
		String year = request.getParameter("year");
		Long ye = Long.parseLong(year);
		
		Idao idao = sqlSession.getMapper(Idao.class);
		HttpSession httpSession = request.getSession();
		Station st_member = (Station)httpSession.getAttribute("st_member");
		ArrayList<List> requests = null;
		if(ye == 0) {
			requests = idao.getReList(st_member.getSt_id());
		}else {
			requests = idao.getAppYeList(st_member.getSt_id(), ye);
		}
		
		model.addAttribute("item", requests);
		
		return "approval";
	}
	
	
	@RequestMapping("sh_year_re.do")
	public String sh_year_re(HttpServletRequest request ,Model model) {
		String year = request.getParameter("year");
		Long ye = Long.parseLong(year);
		
		Idao idao = sqlSession.getMapper(Idao.class);
		HttpSession httpSession = request.getSession();
		Station st_member = (Station)httpSession.getAttribute("st_member");
		ArrayList<List> requests = null;
		if(ye == 0) {
			requests = idao.getAppList(st_member.getSt_id());
		}else {
			requests = idao.getReYeList(st_member.getSt_id(), ye);
		}
		
		model.addAttribute("item", requests);
		
		return "refuse";
	}
	
	
	@RequestMapping(value="inputlist.do",produces = "application/json; charset=utf8")
	@ResponseBody
	public String inputMemo(HttpServletRequest request) {
		String start_st = request.getParameter("start_st");
		String end_st = request.getParameter("end_st");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String hour = request.getParameter("hour");
		String minute = request.getParameter("minute");
		String post = request.getParameter("post");
		String mem_id = request.getParameter("mem_id");
		
		Idao idao = sqlSession.getMapper(Idao.class);
		idao.insertRequest(0L, start_st, Long.parseLong(year), Long.parseLong(month), Long.parseLong(day), Long.parseLong(hour), 
				Long.parseLong(minute), post, 0L, Long.parseLong(mem_id),end_st);
		idao.insertRequest(1L, end_st, Long.parseLong(year), Long.parseLong(month), Long.parseLong(day), Long.parseLong(hour), 
				Long.parseLong(minute), post, 0L, Long.parseLong(mem_id),start_st);
		
		Gson gson = new Gson();
		
		return gson.toJson("");
	}
	
	@RequestMapping(value="inputMember.do",produces = "application/json; charset=utf8")
	@ResponseBody
	public String inputMember(HttpServletRequest request) {
		String mem_name = request.getParameter("mem_name");
		String mem_mid = request.getParameter("mem_mid");
		String mem_pw = request.getParameter("mem_pw");
		String mem_myp = request.getParameter("mem_myp");
		String mem_email = request.getParameter("mem_email");
		String mem_subp = request.getParameter("mem_subp");
		String mem_etc = request.getParameter("mem_etc");
		String mem_age = request.getParameter("mem_age");
		
		System.out.println(mem_name);
		System.out.println(mem_mid);
		System.out.println(mem_pw);
		System.out.println(mem_myp);
		System.out.println(mem_email);
		System.out.println(mem_subp);
		System.out.println(mem_etc);
		System.out.println(mem_age);
		
		Idao idao = sqlSession.getMapper(Idao.class);
		idao.insertMember(mem_name, mem_mid, mem_pw, mem_myp, mem_email, mem_subp, mem_etc ,Long.parseLong(mem_age),"null");
		
		Gson gson = new Gson();
		
		return gson.toJson("");
	}
	
	@RequestMapping(value="checkMember.do",produces = "application/json; charset=utf8")
	@ResponseBody
	public String checkMember(HttpServletRequest request) {
		String mem_mid = request.getParameter("mem_mid");
		
		Idao idao = sqlSession.getMapper(Idao.class);
		Integer ch = idao.chMember(mem_mid);
		
		Gson gson = new Gson();
		
		return gson.toJson(ch);
	}
	
	@RequestMapping(value="chEmail.do",produces = "application/json; charset=utf8")
	@ResponseBody
	public String chEmail(HttpServletRequest request) {
		String mem_email = request.getParameter("mem_email");
		
		Idao idao = sqlSession.getMapper(Idao.class);
		Integer ch = idao.chEmail(mem_email);
		
		Gson gson = new Gson();
		
		return gson.toJson(ch);
	}
	
	@RequestMapping(value="logMember.do",produces = "application/json; charset=utf8")
	@ResponseBody
	public String logMember(HttpServletRequest request) {
		String mem_mid = request.getParameter("mem_mid");
		String mem_pw = request.getParameter("mem_pw");
		
		System.out.println(mem_mid);
		System.out.println(mem_pw);
		
		Idao idao = sqlSession.getMapper(Idao.class);	
		
		Member member = null;
		
		Integer num = idao.logMember(mem_mid, mem_pw);
		if(num == 0) {
			member = new Member(-1L, "error", "error", "error", "error", "error", "error", "error", -1L,"error");
		}else {
			member = idao.getMember(mem_mid, mem_pw);
		}
		
		Gson gson = new Gson();
		
		return gson.toJson(member);
	}
	
	@RequestMapping(value="insertBookmark.do",produces = "application/json; charset=utf8")
	@ResponseBody
	public String insertBookmark(HttpServletRequest request) {
		String book_kind = request.getParameter("book_kind");
		String book_start = request.getParameter("book_start");
		String book_end = request.getParameter("book_end");
		String mem_id = request.getParameter("mem_id");
		
		Idao idao = sqlSession.getMapper(Idao.class);
		idao.insertBookmark(Long.parseLong(book_kind), book_start, book_end, Long.parseLong(mem_id));
		
		Gson gson = new Gson();
		
		return gson.toJson("");
	}
	
	@RequestMapping(value="delBookmark.do",produces = "application/json; charset=utf8")
	@ResponseBody
	public String delBookmark(HttpServletRequest request) {
		String id = request.getParameter("id");
		
		Idao idao = sqlSession.getMapper(Idao.class);
		idao.delBookmark(Long.parseLong(id));
		
		Gson gson = new Gson();
		
		return gson.toJson("");
	}
	
	@RequestMapping(value="getBookmark.do",produces = "application/json; charset=utf8")
	@ResponseBody
	public String getBookmark(HttpServletRequest request) {
		String mem_id = request.getParameter("mem_id");
		System.out.println(mem_id);
		Long id = Long.parseLong(mem_id);
		
		Idao idao = sqlSession.getMapper(Idao.class);
		ArrayList<Bookmark> bookmark = idao.getBookmark(id);
		
		Gson gson = new Gson();
		
		return gson.toJson(bookmark);
	}
	
	
	@RequestMapping(value="updateKey.do",produces = "application/json; charset=utf8")
	@ResponseBody
	public String updateMeskey(HttpServletRequest request) {
		String mem_key = request.getParameter("mem_key");
		String id = request.getParameter("id");
		
		Idao idao = sqlSession.getMapper(Idao.class);
		idao.updatekey(mem_key,Long.parseLong(id));
		
		Gson gson = new Gson();
		
		return gson.toJson("");
	}
	
	@RequestMapping(value="chBookmark.do",produces = "application/json; charset=utf8")
	@ResponseBody
	public String chBookmark(HttpServletRequest request) {
		String book_start = request.getParameter("book_start");
		String mem_id = request.getParameter("mem_id");
		
		Idao idao = sqlSession.getMapper(Idao.class);
		Integer count = idao.chBookmark(book_start, Long.parseLong(mem_id));
		
		Gson gson = new Gson();
		
		return gson.toJson(count);
	}

	@RequestMapping(value="delBusBookmark.do",produces = "application/json; charset=utf8")
	@ResponseBody
	public String delBusBookmark(HttpServletRequest request) {
		String book_start = request.getParameter("book_start");
		String mem_id = request.getParameter("mem_id");
		
		Idao idao = sqlSession.getMapper(Idao.class);
		idao.delBusBookmark(book_start, Long.parseLong(mem_id));
		
		Gson gson = new Gson();
		
		return gson.toJson("");
	}
	
	@RequestMapping(value="returnRequest.do",produces = "application/json; charset=utf8")
	@ResponseBody
	public String returnRequest(HttpServletRequest request) {
		String mem_id = request.getParameter("mem_id");
		System.out.println(mem_id);
		
		Idao idao = sqlSession.getMapper(Idao.class);
		ArrayList<Request> requests = idao.returnRequest(Long.parseLong(mem_id));
		
		Gson gson = new Gson();
		
		return gson.toJson(requests);
	}
	
	@RequestMapping(value="updateMember.do",produces = "application/json; charset=utf8")
	@ResponseBody
	public String updateMember(HttpServletRequest request) {
		String mem_name = request.getParameter("mem_name");
		String mem_myp = request.getParameter("mem_myp");
		String mem_subp = request.getParameter("mem_subp");
		String mem_etc = request.getParameter("mem_etc");
		String mem_age = request.getParameter("mem_age");
		String id = request.getParameter("id");
		
		Idao idao = sqlSession.getMapper(Idao.class);
		idao.updateMember(mem_name, mem_myp, mem_subp, mem_etc, Long.parseLong(mem_age), Long.parseLong(id));
		
		Gson gson = new Gson();
		
		return gson.toJson("");
	}
	
	@RequestMapping(value="updatePw.do",produces = "application/json; charset=utf8")//아직 구현 안함. 비밀번호 변경
	@ResponseBody
	public String updatePw(HttpServletRequest request) {
		String mem_pw = request.getParameter("mem_pw");
		String id = request.getParameter("id");
		
		Idao idao = sqlSession.getMapper(Idao.class);
		idao.updatePw(mem_pw, Long.parseLong(id));
		
		Gson gson = new Gson();
		
		return gson.toJson("");
	}
	
	@RequestMapping(value="insertNotice.do",produces = "application/json; charset=utf8")
	@ResponseBody
	public String insertNotice(HttpServletRequest request) {
		String notice_title = request.getParameter("notice_title");
		String notice_contents = request.getParameter("notice_contents");
		String notice_date = request.getParameter("notice_date");
		
		Idao idao = sqlSession.getMapper(Idao.class);
		idao.insertNotice(notice_title, notice_contents, notice_date);
		
		Gson gson = new Gson();
		
		return gson.toJson("");
	}
	
	@RequestMapping(value="selectNotice.do",produces = "application/json; charset=utf8")
	@ResponseBody
	public String selectNotice(HttpServletRequest request) {
		String ok = request.getParameter("ok");
		System.out.println(ok);
		
		Idao idao = sqlSession.getMapper(Idao.class);
		ArrayList<Notice> notices = null;
		
		if(ok.equals("ok")){
			notices = idao.selectNotice();
		}
		
		Gson gson = new Gson();
		
		return gson.toJson(notices);
	}
	
	@RequestMapping(value="updateNotice.do",produces = "application/json; charset=utf8")
	@ResponseBody
	public String updateNotice(HttpServletRequest request) {
		String notice_title = request.getParameter("notice_title");
		String notice_contents = request.getParameter("notice_contents");
		String notice_date = request.getParameter("notice_date");
		String id = request.getParameter("id");
		
		Idao idao = sqlSession.getMapper(Idao.class);
		idao.updateNotice(notice_title, notice_contents, notice_date, Long.parseLong(id));
		
		Gson gson = new Gson();
		
		return gson.toJson("");
	}
	
	@RequestMapping(value="deleteNotice.do",produces = "application/json; charset=utf8")
	@ResponseBody
	public String deleteNotice(HttpServletRequest request) {
		String id = request.getParameter("id");
		
		Idao idao = sqlSession.getMapper(Idao.class);
		idao.deleteNotice(Long.parseLong(id));
		
		Gson gson = new Gson();
		
		return gson.toJson("");
	}
	
	@RequestMapping(value="selectDelBookmark.do",produces = "application/json; charset=utf8")
	@ResponseBody
	public String selectDelBookmark(HttpServletRequest request) {
		String mem_id = request.getParameter("mem_id");
		String book_start = request.getParameter("book_start");
		String book_end = request.getParameter("book_end");
		
		Idao idao = sqlSession.getMapper(Idao.class);
		Bookmark bookmark = idao.selectBookmark(Long.parseLong(mem_id), book_start, book_end);
		idao.delBookmark(bookmark.getId());
		
		Gson gson = new Gson();
		
		return gson.toJson("");
	}
	
	@RequestMapping("delpost.do")
	public String delpost() {
		
		return "delpost";
	}
	
	@RequestMapping("refusal.do")
	public String refusal(HttpServletRequest request) {
		String id = request.getParameter("id");
		
		Idao idao = sqlSession.getMapper(Idao.class);
		
		
		List list = idao.chMyList(Long.parseLong(id));
		String title = list.getMember().getMem_name() + "님";
		String body = null;
		if(list.getRequest().getLi_kind() == 0) {
			body = list.getRequest().getLi_station()+"역 출발이 거절되었습니다.";
		}else {
			body = list.getRequest().getLi_station()+"역 도착이 거절되었습니다.";
		}
		String sendKey = list.getMember().getMem_key();
		
		final String apiKey = "AAAA34bus-Y:APA91bGntKv50NXJSnMZoJ07R-Ti0UorR6vZ-5FzRG1285IYqvtEj3y4SF4P8Aot4fe9u1TChZ2gyzA_mEZKhqXeNT307fAqNNS8WKEaldQXRiG-GK0v5ecreOvEnWvCoMFuzPoml6a3";
        URL url = null;
        HttpURLConnection conn = null;
        
		try {
			url = new URL("https://fcm.googleapis.com/fcm/send");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        conn.setDoOutput(true);
        
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "key=" + apiKey);

        conn.setDoOutput(true);
        
        String userId =(String) request.getSession().getAttribute("ssUserId");
        
        String input = "{\"notification\" : {\"title\" : \""+title+"\", \"body\" : \""+body+"\"}, \"to\":\""+sendKey+"\"}";

        OutputStream os = null;
        int responseCode = 0;
        BufferedReader in = null;
        String inputLine;
        StringBuffer response = new StringBuffer();
        
        try {
        	os = conn.getOutputStream();
			os.write(input.getBytes("UTF-8"));
			os.flush();
			os.close();
			responseCode = conn.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
	        System.out.println("Post parameters : " + input);
	        System.out.println("Response Code : " + responseCode);
	        
	        in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((inputLine = in.readLine()) != null) {
			    response.append(inputLine);
			}
			in.close();
	        
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

        
        // print result
        System.out.println(response.toString());
		
		
		
		idao.updateReList(Long.parseLong(id));
		
		return "redirect:delpost.do";
	}
	
}


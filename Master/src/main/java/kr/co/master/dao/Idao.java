package kr.co.master.dao;

import java.util.ArrayList;

import kr.co.master.dto.Bookmark;
import kr.co.master.dto.List;
import kr.co.master.dto.Member;
import kr.co.master.dto.Meskey;
import kr.co.master.dto.Notice;
import kr.co.master.dto.Request;
import kr.co.master.dto.Station;

public interface Idao {
	public void insertStation(String st_id, String st_pw, String st_name, String st_phone);
	public Integer stationCount(String st_id);
	public Integer login(String st_id,String st_pw);
	public Station getStationInfo(String st_id,String st_pw);
	public void insertRequest(Long li_kind, String li_station, Long li_year, Long li_month, Long li_day, Long li_hour,
			Long li_minute, String li_post, Long li_appr, Long mem_id, String li_otherSta);
	public ArrayList<List> getList(String li_station);
	public ArrayList<List> getAppList(String li_station);
	public ArrayList<List> getReList(String li_station);
	public List getPost(Long id);
	public ArrayList<List> getBooking(String li_station);
	public void updateList(Long id);
	public void updateReList(Long id);
	public void delList(Long id);
	
	public ArrayList<List> getAllShList(String li_station, Long li_month, Long li_day, String mem_name);
	public ArrayList<List> getMoList(String li_station, Long li_month);
	public ArrayList<List> getMoDaList(String li_station, Long li_month, Long li_day);
	public ArrayList<List> getMoDaKiList(String li_station, Long li_month, Long li_day, Long li_kind);
	public ArrayList<List> getMoDaKiNaList(String li_station, Long li_month, Long li_day, Long li_kind, String mem_name);
	public ArrayList<List> getMoNaList(String li_station, Long li_month, String mem_name);
	public ArrayList<List> getMoKiList(String li_station, Long li_month, Long li_kind);
	public ArrayList<List> getMoDaNaList(String li_station, Long li_month, Long li_day, String mem_name);
	public ArrayList<List> getMoKiNaList(String li_station, Long li_month, Long li_kind, String mem_name);
	public ArrayList<List> getDaList(String li_station, Long li_day);
	public ArrayList<List> getDaKiList(String li_station, Long li_day, Long li_kind);
	public ArrayList<List> getDaNaList(String li_station, Long li_day, String mem_name);
	public ArrayList<List> getDaNaKiList(String li_station, Long li_day, String mem_name, Long li_kind);
	public ArrayList<List> getNaList(String li_station, String mem_name);
	public ArrayList<List> getNaKiList(String li_station, String mem_name, Long li_kind);
	public ArrayList<List> getKiList(String li_station, Long li_kind);
	public ArrayList<List> getAllShTwoList(String li_station, Long li_month, Long li_day, String mem_name, Long li_kind);
	
	public ArrayList<List> getAppAllShList(String li_station, Long li_month, Long li_day, String mem_name);
	public ArrayList<List> getAppMoList(String li_station, Long li_month);
	public ArrayList<List> getAppMoDaList(String li_station, Long li_month, Long li_day);
	public ArrayList<List> getAppMoDaKiList(String li_station, Long li_month, Long li_day, Long li_kind);
	public ArrayList<List> getAppMoDaKiNaList(String li_station, Long li_month, Long li_day, Long li_kind, String mem_name);
	public ArrayList<List> getAppMoNaList(String li_station, Long li_month, String mem_name);
	public ArrayList<List> getAppMoKiList(String li_station, Long li_month, Long li_kind);
	public ArrayList<List> getAppMoDaNaList(String li_station, Long li_month, Long li_day, String mem_name);
	public ArrayList<List> getAppMoKiNaList(String li_station, Long li_month, Long li_kind, String mem_name);
	public ArrayList<List> getAppDaList(String li_station, Long li_day);
	public ArrayList<List> getAppDaKiList(String li_station, Long li_day, Long li_kind);
	public ArrayList<List> getAppDaNaList(String li_station, Long li_day, String mem_name);
	public ArrayList<List> getAppDaNaKiList(String li_station, Long li_day, String mem_name, Long li_kind);
	public ArrayList<List> getAppNaList(String li_station, String mem_name);
	public ArrayList<List> getAppNaKiList(String li_station, String mem_name, Long li_kind);
	public ArrayList<List> getAppKiList(String li_station, Long li_kind);
	public ArrayList<List> getAppAllShTwoList(String li_station, Long li_month, Long li_day, String mem_name, Long li_kind);
	
	public ArrayList<List> getReAllShList(String li_station, Long li_month, Long li_day, String mem_name);
	public ArrayList<List> getReMoList(String li_station, Long li_month);
	public ArrayList<List> getReMoDaList(String li_station, Long li_month, Long li_day);
	public ArrayList<List> getReMoDaKiList(String li_station, Long li_month, Long li_day, Long li_kind);
	public ArrayList<List> getReMoDaKiNaList(String li_station, Long li_month, Long li_day, Long li_kind, String mem_name);
	public ArrayList<List> getReMoNaList(String li_station, Long li_month, String mem_name);
	public ArrayList<List> getReMoKiList(String li_station, Long li_month, Long li_kind);
	public ArrayList<List> getReMoDaNaList(String li_station, Long li_month, Long li_day, String mem_name);
	public ArrayList<List> getReMoKiNaList(String li_station, Long li_month, Long li_kind, String mem_name);
	public ArrayList<List> getReDaList(String li_station, Long li_day);
	public ArrayList<List> getReDaKiList(String li_station, Long li_day, Long li_kind);
	public ArrayList<List> getReDaNaList(String li_station, Long li_day, String mem_name);
	public ArrayList<List> getReDaNaKiList(String li_station, Long li_day, String mem_name, Long li_kind);
	public ArrayList<List> getReNaList(String li_station, String mem_name);
	public ArrayList<List> getReNaKiList(String li_station, String mem_name, Long li_kind);
	public ArrayList<List> getReKiList(String li_station, Long li_kind);
	public ArrayList<List> getReAllShTwoList(String li_station, Long li_month, Long li_day, String mem_name, Long li_kind);
	
	public ArrayList<List> getYeList(String li_station, Long li_year);
	public ArrayList<List> getAppYeList(String li_station, Long li_year);
	public ArrayList<List> getReYeList(String li_station, Long li_year);
	
	public void insertMember(String mem_name, String mem_mid, String mem_pw, String mem_myp, String mem_email, 
			String mem_subp, String mem_etc, Long mem_age, String mem_key);
	public Integer chMember(String mem_mid);
	public Integer chEmail(String mem_email);
	public Integer logMember(String mem_mid, String mem_pw);
	public Member getMember(String mem_mid, String mem_pw);
	public void insertBookmark(Long book_kind, String book_start, String book_end, Long mem_id);
	public void delBookmark(Long id);
	public ArrayList<Bookmark> getBookmark(Long mem_id);
	
	public List chMyList(Long id);
	
	public void updatekey(String key, Long id);
	public Integer chBookmark(String book_start, Long mem_id);
	public void delBusBookmark(String book_start, Long mem_id);
	public ArrayList<Request> returnRequest(Long mem_id);
	
	public void updateMember(String mem_name, String mem_myp, String mem_subp, String mem_etc, Long mem_age, Long id);
	public void updatePw(String mem_pw, Long id);
	
	public void insertNotice(String notice_title, String notice_contents, String notice_date);
	public ArrayList<Notice> selectNotice();
	public void updateNotice(String notice_title, String notice_contents, String notice_date, Long id);
	public Bookmark selectBookmark(Long mem_id, String book_start, String book_end);
	public void deleteNotice(Long id);
}

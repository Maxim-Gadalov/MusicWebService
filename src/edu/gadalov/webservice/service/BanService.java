package edu.gadalov.webservice.service;

import java.util.ArrayList;
import java.util.List;

import edu.gadalov.webservice.dao.BanListDAO;
import edu.gadalov.webservice.entity.BanList;

public class BanService {
	public boolean banUser(BanList entity){
		boolean result = false;
		BanListDAO dao = new BanListDAO();
		try{
			result = dao.create(entity);
		} finally{
			dao.close(dao.getConnection());
		}
		return result;
	}
	public boolean removeBan(int idUser){
		boolean result = false;
		BanListDAO dao = new BanListDAO();
		try{
			result = dao.deleteById(idUser);
		} finally{
			dao.close(dao.getConnection());
		}
		return result;
	}
	public List<BanList> getBanList(){
		List<BanList> list = new ArrayList<>();
		BanListDAO dao = new BanListDAO();
		try{
			list = dao.findAll();
		} finally{
			dao.close(dao.getConnection());
		}
		return list;
	}
	public boolean checkBanUser(int idUser){
		BanList ban = null;
		BanListDAO dao = new BanListDAO();
		try{
			ban = dao.findById(idUser);
		}finally{
			dao.close(dao.getConnection());
		}
		return (ban == null);
	}
	public BanList getBanInfo(int idUser){
		BanList ban = null;
		BanListDAO dao = new BanListDAO();
		try{
			ban = dao.findById(idUser);
		} finally{
			dao.close(dao.getConnection());
		}
		return ban;
	}

}

package sdig.service;

import sdig.model.*;
import sdig.util.C3P0Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class GetKQInfo {
	public ArrayList<KqOne> getkqInfo(String orgid,String orgname,String ym) {
		ArrayList<KqOne> list=new ArrayList<KqOne>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		//部门、年、月、部门id
		Date date = null;
		try {
			date = sdf.parse(ym);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		int year = date.getYear() + 1900;
		int month = date.getMonth() + 1;
		//通过视图查询
		PreparedStatement dd02pst=null;
		ResultSet dd02rs=null;
		PreparedStatement dd03pst=null;
		ResultSet dd03rs=null;
		Connection dbconnection = C3P0Utils.getConnection();
		try {
			KqOne info=new KqOne();
			info.setKqorgname(orgname);
			info.setKqn(year);
			info.setKqy(month);
			list.add(info);
			//获得不重复的星期和日期
			StringBuffer kq02String =new StringBuffer("");
			kq02String.append("select kqz,kqr from kqView where kqorgid='"+orgid+"' and kqn="+year+" and kqy="+month+"");
			dd02pst = dbconnection.prepareStatement(kq02String.toString());
			dd02rs=dd02pst.executeQuery();
			ArrayList<KqZRtwo> kqZRlist = new ArrayList<>();
			while (dd02rs.next()) {
				KqZRtwo kqzrInfo = new KqZRtwo();
				kqzrInfo.setKqz(dd02rs.getString("kqz"));
				kqzrInfo.setKqr(dd02rs.getString("kqr"));
				kqZRlist.add(kqzrInfo);
			}
			ArrayList zrresultList=new ArrayList();              //创建新集合
			Iterator iterator=kqZRlist.iterator();         //获取原集合的迭代器
			while(iterator.hasNext()){                      //遍历原集合
				Object obj=iterator.next();
				if(!zrresultList.contains(obj)){                 //若不存在，则加入新集合
					zrresultList.add(obj);
				}
			}
			if (zrresultList.size()>0) {
				list.get(0).setKqzrlist(zrresultList);
			}
			//获得不重复姓名，考勤类型
			StringBuffer kq03String =new StringBuffer("");
			kq03String.append("select kqname,kqtype from kqView where kqorgid='"+orgid+"' and kqn="+year+" and kqy="+month+"");
			dd03pst = dbconnection.prepareStatement(kq03String.toString());
			dd03rs=dd03pst.executeQuery();
			ArrayList<KqNamethree> kqnamelist = new ArrayList<>();
			while (dd03rs.next()) {
				KqNamethree kqnameInfo = new KqNamethree();
				kqnameInfo.setName(dd03rs.getString("kqname"));
				ArrayList<KqTypefour> kqtypeList = new ArrayList<KqTypefour>();
				KqTypefour kqTypeInfo = new KqTypefour();
				kqTypeInfo.setKqtype(dd03rs.getString("kqtype"));
				if(dd03rs.getString("kqtype").contains("工作日")){
					kqTypeInfo.setKqtype("√");
				}
				kqtypeList.add(kqTypeInfo);
				kqnameInfo.setKqTypelist(kqtypeList);
				kqnamelist.add(kqnameInfo);
			}

			LinkedHashMap<String, KqNamethree> tempMap = new  LinkedHashMap<String,KqNamethree>();
			//去掉重复的key
			for (int j = 0; j < kqnamelist.size(); j++) {
				KqNamethree kqNamethreeInfo = kqnamelist.get(j);
				String name = kqNamethreeInfo.getName();
				if(tempMap.containsKey(name)){
					KqNamethree newDataReport= new KqNamethree();
					newDataReport.setName(name);
					//合并相同key的value
					newDataReport.setKqTypelist(tempMap.get(name).getKqTypelist());
					newDataReport.getKqTypelist().add(kqNamethreeInfo.getKqTypelist().get(0));
					//HashMap不允许key重复，当有key重复时，前面key对应的value值会被覆盖
					tempMap.put(name,newDataReport);
				}
				else{
					tempMap.put(name,kqNamethreeInfo);
				}
			}
			//去除重复key的list
			ArrayList<KqNamethree> list2=new ArrayList<KqNamethree>();
			for(String temp:tempMap.keySet()){
				list2.add(tempMap.get(temp));
			}
			if (list2.size()>0) {
				list.get(0).getKqzrlist().get(0).setKqnamelist(list2);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally {
			C3P0Utils.closeAll(dd02pst,dd02rs,dd03pst,dd03rs,dbconnection);
		}					
		return list;		
	}
	
}

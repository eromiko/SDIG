package sdig.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.sun.xml.internal.bind.unmarshaller.InfosetScanner;

import sdig.model.QjlbInfo;
import sdig.util.C3P0Utils;

public class GetQjlbInfo {
	public ArrayList<QjlbInfo> getQjlbInfo() {
		ArrayList<QjlbInfo> list=new ArrayList<QjlbInfo>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer empString =new StringBuffer("");
		//视图
		empString.append("select name,bm,gw,qjlb,qjsj,qjts,sysj from sdxjmxb");
		PreparedStatement ddpst=null;
		ResultSet ddrs=null;
		Connection dbconnection = C3P0Utils.getConnection();
		try {
			ddpst = dbconnection.prepareStatement(empString.toString());
			ddrs=ddpst.executeQuery();
			int i=1;
			while (ddrs.next()) {
				QjlbInfo info=new QjlbInfo();
				info.setIndex(i);	
				info.setName(ddrs.getString("name"));
				info.setBm(ddrs.getString("bm"));
				info.setGw(ddrs.getString("gw"));
				info.setQjlb(ddrs.getString("qjlb"));
				info.setQjcs("1");
				info.setQjsj(sdf.format(ddrs.getDate("qjsj")));
				info.setQjts(ddrs.getBigDecimal("qjts"));
				info.setSysj(ddrs.getBigDecimal("sysj"));
				list.add(info);
				i++;
			}									
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally {
			C3P0Utils.closeAll(ddpst,ddrs,dbconnection);
		}					
		return list;		
	}
	
}

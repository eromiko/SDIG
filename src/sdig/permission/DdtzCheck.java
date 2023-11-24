package sdig.permission;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import sdig.service.DdtzCheckJieDian;
import sdig.util.BlobAndBase64Util;
import sdig.util.C3P0Utils;
/**
 * 
 * 集团终审过了
 */
@WebServlet("/DdtzCheckJieDianServlet")
public class DdtzCheck extends HttpServlet {
	
	public DdtzCheck() {		
	}
	DdtzCheckJieDian service=new DdtzCheckJieDian();
	private static ObjectMapper MAPPER =  new ObjectMapper();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		super.doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String billid=req.getParameter("billid");
		boolean result = service.DdtzJD(billid);
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.getWriter().print(result);
	}
}

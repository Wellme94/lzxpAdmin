package com.etc.lzxp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.lzxp.service.SellerMessageService;
import com.google.gson.Gson;

/**
 * Servlet implementation class SellerMessageServlet
 * �̼���Ϣ���ѵ�Servlet
 */
@WebServlet("/SellerMessageServlet")
public class SellerMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����������Ӧ����
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//����jason��ʽ����
		response.setContentType("application/json");
		PrintWriter out= response.getWriter();
		SellerMessageService sms = new SellerMessageService();

		if (request.getParameter("op")!=null) {
			//��ȡop
			String op = request.getParameter("op");
			if ("message".equals(op)) {
				/**
				 * ҳ����Ϣ�ֲ�ˢ��
				 */
				//����service����
				boolean hasOrder = sms.hasOrderMessage();
				//��ӡ
				printJson(out, hasOrder);
			
			}else if ("cancelMessage".equals(op)) {
				/**
				 * ȡ����Ϣ����
				 */
				printJson(out, sms.cancelMessage());
			}
			
			
			
		}
	}
	
	
	
				//ajax��ӡ
			private void printJson(PrintWriter out,Object result){
				//����Json
				Gson gson = new Gson();
				//ת����Json��ʽ
				String str = gson.toJson(result);
				//��ӡ
				out.print(str);
				//�ͷ���Դ
				out.close();
			}

}

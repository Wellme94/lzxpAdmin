package com.etc.lzxp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.lzxp.entity.GoodsSort;
import com.etc.lzxp.entity.Goods_ltype;
import com.etc.lzxp.entity.Goods_stype;
import com.etc.lzxp.service.GoodsSortService;

/**
 * Servlet implementation class GoodsServlet
 */
@WebServlet("/GoodsSortServlet")
public class GoodsSortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<GoodsSort> list = new ArrayList<GoodsSort>();

		GoodsSortService gss = new GoodsSortService();

		if (request.getParameter("op") != null) {

			// ��op��ֵȡ���� �ж����ֵ
			String op = request.getParameter("op");

			if (op.equals("getAllGoodsSort")) {
				// ��ѯ��Ʒ���࣬����Service�еķ���
				list = gss.getAllGoodsSort();

			} else if (op.equals("deleteType")) {

				// ɾ������
				String stypeIdStr = request.getParameter("stypeIdStr");

				String[] stypeId = stypeIdStr.split(",");

				for (int i = 0; i < stypeId.length; i++) {

					boolean updateSucess = gss.deleteType(Integer.parseInt(stypeId[i]));

					request.setAttribute("updateSucess", updateSucess);

				}

				list = gss.getAllGoodsSort();

			}
		}

		// ��ȡ��Ʒ�Ĵ���
		List<Goods_ltype> ltypeList = gss.getLtype();

		// ������Ʒ�������
		request.setAttribute("ltypeList", ltypeList);

		// ��ȡ��Ʒ��С��
		List<Goods_stype> stypeList = gss.getStype();

		// ������ƷС�����
		request.setAttribute("stypeList", stypeList);

		request.setAttribute("list", list);

		// ��ת��
		request.getRequestDispatcher("goods-sort.jsp").forward(request, response);

	}

}

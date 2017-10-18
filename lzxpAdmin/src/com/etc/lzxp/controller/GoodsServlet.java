package com.etc.lzxp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.lzxp.entity.Goods;
import com.etc.lzxp.service.GoodsService;

/**
 * Servlet implementation class GoodsServlet
 */
@WebServlet("/GoodsServlet")
public class GoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	GoodsService gs=new GoodsService();
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
		
		List<Goods> list =new ArrayList<Goods>();
		
		if (request.getParameter("op") != null) {

			// 将op的值取出来 判断这个值
			String op = request.getParameter("op");

			if (op.equals("getAllGoods")) {
				// 查询所有，调用Service中的方法 
				list = gs.getAllGoods();

			}  else if (op.equals("getGoodsByGoodsName")) {

				// 根据GoodsName做模糊查询
				String goodsName = request.getParameter("goodsName");
			
				// 调用service得到模糊查询返回的结果
				list = gs.getGoodsByGoodsName(goodsName);

				// 设置传递的数据
				request.setAttribute("goodsName", goodsName);

			} else if (op.equals("addGoods")) {
				// 增加操作
				String goodsName = request.getParameter("goodsName");
				int stypeId = Integer.parseInt(request.getParameter("stypeId"));
				double goodsPrice = Double.parseDouble(request.getParameter("goodsPrice"));
				String goodsContent = request.getParameter("goodsContent");
				int stypeStock = Integer.parseInt(request.getParameter("stypeStock"));
				int goodsState = Integer.parseInt(request.getParameter("goodsState"));

				//得到一个goods对象
				Goods goods = new Goods(0,goodsName, stypeId, goodsPrice, goodsContent,stypeStock,goodsState);
				// 调用service的add方法
				gs.addGoods(goods);

				list = gs.getAllGoods();

			}  else if (op.equals("deleteGoods")) {

				// 删除操作
				String goodsIdStr = request.getParameter("goodsIdStr");
				
				System.out.println(goodsIdStr);
				
				String[] goodsId=goodsIdStr.split(",");
				
				for(int i=0;i<goodsId.length;i++){
					
				gs.deleteGoods(Integer.parseInt(goodsId[i]));
				
				}

				list = gs.getAllGoods();

			}else if (op.equals("updateGoods")) {

				// 修改操作
				String goodsName = request.getParameter("goodsName");
				int stypeId = Integer.parseInt(request.getParameter("stypeId"));
				double goodsPrice = Double.parseDouble(request.getParameter("goodsPrice"));
				String goodsContent = request.getParameter("goodsContent");
				int stypeStock = Integer.parseInt(request.getParameter("stypeStock"));
				int goodsState = Integer.parseInt(request.getParameter("goodsState"));

				Goods goods=new Goods(0,goodsName, stypeId,goodsPrice,goodsContent,stypeStock,goodsState);
				
				gs.updateGoods(goods);

				list = gs.getAllGoods();

			}
		}

		// 将结果返回给goods-list.jsp
		// 先设置要传递的数据
		request.setAttribute("list", list);

		// 再转发
		request.getRequestDispatcher("goods-list.jsp").forward(request, response);
	
	}

}

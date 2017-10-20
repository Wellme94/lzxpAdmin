package com.etc.lzxp.service;

import java.util.List;

import com.etc.lzxp.dao.GoodsOrderDao;
import com.etc.lzxp.entity.Goods_order;

public class GoodsOrderService {

	GoodsOrderDao god=new GoodsOrderDao();
	/**
	 * ��ʾ���н�����Ϣ
	 * @return
	 */
	public List<Goods_order> getAllOrder(){
		return god.getAllOrder();
	}
	
	/**
	 * ����IDɾ��
	 * @param orderId
	 * @return
	 */
	public boolean deleteOrder(int orderId){
		return god.deleteOrder(orderId);
	}
	
	/**
	 * ����ʱ�䷶Χ��ѯ
	 * @param mindate
	 * @param maxdate
	 * @return
	 */
	public List<Goods_order> getOrderByDate(String mindate, String maxdate){
		return god.getOrderByDate(mindate, maxdate);
	}
}

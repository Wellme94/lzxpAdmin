package com.etc.lzxp.service;

import java.util.List;

import com.etc.lzxp.dao.GoodsSortDao;
import com.etc.lzxp.entity.GoodsSort;
import com.etc.lzxp.entity.Goods_ltype;
import com.etc.lzxp.entity.Goods_stype;
import com.etc.util.BaseDao;

public class GoodsSortService {

	GoodsSortDao gsd=new GoodsSortDao();
	/**
	 * ��ʾ��Ʒ����
	 * @return
	 */
	public List<GoodsSort> getAllGoodsSort(){
		return gsd.getAllGoodsSort();
	}
	
	/**
	 * ������ƷС��idɾ��
	 * @param stypeId
	 * @return
	 */
	public boolean deleteType(int stypeId){
		return gsd.deleteType(stypeId);
	}
	
	/**
	 * ��ȡ��ƷС��
	 * 
	 * @return
	 */
	public List<Goods_stype> getStype() {
		return (List<Goods_stype>) BaseDao.select("select * from goods_stype", Goods_stype.class, null);
	}
	
	/**
	 * ��ȡ��Ʒ����
	 * 
	 * @return
	 */
	public List<Goods_ltype> getLtype() {
		return (List<Goods_ltype>) BaseDao.select("select * from goods_ltype", Goods_ltype.class, null);
	}
}

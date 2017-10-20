package com.etc.lzxp.dao;

import java.util.List;

import com.etc.lzxp.entity.GoodsSort;
import com.etc.lzxp.entity.Goods_ltype;
import com.etc.lzxp.entity.Goods_stype;
import com.etc.util.BaseDao;

public class GoodsSortDao {

	/**
	 * ��Ʒ������ʾ
	 * @return
	 */
	public List<GoodsSort> getAllGoodsSort() {
		return (List<GoodsSort>) BaseDao.select("select * from goods_ltype,goods_stype where goods_ltype.ltypeid=goods_stype.ltypeid order by ltypename", GoodsSort.class, null);
	}
	
	/**
	 * ������ƷС��idɾ��
	 * @param stypeId
	 * @return
	 */
	public boolean deleteType(int stypeId) {
		return BaseDao.execute("delete from goods_stype where stypeId=?", stypeId) > 0;
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

package com.etc.lzxp.dao;

import java.util.ArrayList;
import java.util.List;

import com.etc.lzxp.entity.Goods;
import com.etc.util.BaseDao;

public class GoodsDao {
	/**
	 * 显示商品所有信息
	 * 
	 * @return
	 */
	public List<Goods> getAllGoods() {
		return (List<Goods>) BaseDao.select("select * from goods", Goods.class, null);
	}

	/**
	 * 通过ID查找
	 * 
	 * @param goodsId
	 * @return
	 */
	public List<Goods> getGoodsBygoodsId(int goodsId) {
		return (List<Goods>) BaseDao.select("select * from Goods where goodsId like ?", Goods.class, goodsId);
	}

	/**
	 * 通过商品名搜索
	 * 
	 * @param goodsName
	 * @return
	 */
	public List<Goods> getGoodsByGoodsName(String goodsName) {
		List<Goods> list = new ArrayList<>();

		if ((goodsName.equals(""))) {
			// 如果搜索栏为空
			list = (List<Goods>) BaseDao.select("select * from goods", Goods.class);
		} else {
			list = (List<Goods>) BaseDao.select("select * from goods where GoodsName like ? ", Goods.class,
					"%" + goodsName + "%");
		}
		return list;
	}

	/**
	 * 新增商品
	 * 
	 * @param goods
	 * @return
	 */
	public boolean addGoods(Goods goods) {
		return BaseDao.execute("insert into  Goods values(null,?,?,?,?,?,?)", goods.getGOODSNAME(),
				goods.getSTYPEID(), goods.getGOODSPRICE(), goods.getGOODSCONTENT(), goods.getGOODSSTOCK(),
				goods.getGOODSSTATE()) > 0;
	}

	/**
	 * 根据商品ID删除
	 * 
	 * @param goodsId
	 * @return
	 */
	public boolean deleteGoods(int goodsId) {
		return BaseDao.execute("delete from Goods where goodsId=?", goodsId) > 0;
	}
	
	/**
	 * 修改商品信息
	 * @param goods
	 * @return
	 */
	public boolean updateGoods(Goods goods) {
		return BaseDao.execute(
				"update Goods set goodsName=?,stypeId=?,goodsPrice=?,goodsContent=?,goodsStock=?goodsState=? where goodsId=?",goods.getGOODSNAME(),goods.getSTYPEID(),goods.getGOODSPRICE(),goods.getGOODSCONTENT(),goods.getGOODSSTOCK(),goods.getGOODSSTATE(),goods.getGOODSID()) > 0;
	}

}

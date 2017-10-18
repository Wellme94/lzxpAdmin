package com.etc.lzxp.service;

import java.util.List;

import com.etc.lzxp.dao.GoodsDao;
import com.etc.lzxp.entity.Goods;

public class GoodsService {
	GoodsDao gd = new GoodsDao();

	/**
	 * 显示所有商品信息
	 * 
	 * @return
	 */
	public List<Goods> getAllGoods() {
		return gd.getAllGoods();
	}

	/**
	 * 通过ID查找
	 * 
	 * @param goodsId
	 * @return
	 */
	public List<Goods> getGoodsBygoodsId(int goodsId) {
		return gd.getGoodsBygoodsId(goodsId);
	}

	/**
	 * 通过商品查找
	 * 
	 * @param goodsName
	 * @return
	 */
	public List<Goods> getGoodsByGoodsName(String goodsName) {
		return gd.getGoodsByGoodsName(goodsName);
	}

	/**
	 * 新增商品
	 * 
	 * @param goods
	 * @return
	 */
	public boolean addGoods(Goods goods) {
		return gd.addGoods(goods);
	}

	/**
	 * 通过ID删除商品
	 * 
	 * @param goodsId
	 * @return
	 */
	public boolean deleteGoods(int goodsId) {
		return gd.deleteGoods(goodsId);
	}
	
	/**
	 * 修改商品信息
	 * @param goods
	 * @return
	 */
	public boolean updateGoods(Goods goods){
		return gd.updateGoods(goods);
	}
}

package com.etc.lzxp.service;

import java.util.List;

import com.etc.lzxp.dao.GoodsDao;
import com.etc.lzxp.entity.Goods;
import com.etc.lzxp.entity.GoodsAndStype;
import com.etc.lzxp.entity.GoodsStypePicture;
import com.etc.lzxp.entity.Goods_stype;

public class GoodsService {
	GoodsDao gd = new GoodsDao();

	/**
	 * ��ʾ������Ʒ��Ϣ
	 * 
	 * @return
	 */
	public List<GoodsStypePicture> getAllGoods() {
		return gd.getAllGoods();
	}

	/**
	 * ͨ��ID����
	 * 
	 * @param goodsId
	 * @return
	 */
	public List<Goods> getGoodsBygoodsId(int goodsId) {
		return gd.getGoodsBygoodsId(goodsId);
	}

	/**
	 * ͨ��IDɾ����Ʒ
	 * 
	 * @param goodsId
	 * @return
	 */
	public boolean deleteGoods(int goodsId) {
		return gd.deleteGoods(goodsId);
	}

	/**
	 * �޸���Ʒ��Ϣ
	 * 
	 * @param goods
	 * @return
	 */
	public boolean updateGoods(Goods goods, String goodsStypeName) {
		return gd.updateGoods(goods, goodsStypeName);
	}

	/**
	 * ��ȡ��ƷС��
	 * 
	 * @return
	 */
	public List<Goods_stype> getStype() {
		return gd.getStype();
	}

	/**
	 * ������Ʒ��ͨ����������ȡ��ȡС���ͣ�
	 * 
	 * @param stypeName
	 * @return
	 */
	public boolean addGoods(Goods goods, String stypeName,String pictureAddress) {
		return gd.addGoods(goods, stypeName,pictureAddress);
	}

	/**
	 * ��Ʒ�ϼ�
	 * 
	 * @param goodsId
	 * @return
	 */
	public boolean upGoods(int goodsId) {
		return gd.upGoods(goodsId);
	}

	/**
	 * ��Ʒ�¼�
	 * 
	 * @param goodsId
	 * @return
	 */
	public boolean downGoods(int goodsId) {
		return gd.downGoods(goodsId);
	}
}

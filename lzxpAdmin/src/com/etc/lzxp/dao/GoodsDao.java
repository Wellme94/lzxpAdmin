package com.etc.lzxp.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.etc.lzxp.entity.Goods;
import com.etc.lzxp.entity.GoodsAndStype;
import com.etc.lzxp.entity.GoodsStypePicture;
import com.etc.lzxp.entity.Goods_stype;
import com.etc.util.BaseDao;

public class GoodsDao {
	/**
	 * 显示商品所有信息
	 * 
	 * @return
	 */
	public List<GoodsStypePicture> getAllGoods() {
		return (List<GoodsStypePicture>) BaseDao.select(
				"select * from goods,goods_stype,goods_picture where goods.stypeid=goods_stype.stypeid and goods_picture.goodsid=goods.goodsid", GoodsStypePicture.class, null);
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
	 * 根据商品ID删除
	 * 
	 * @param goodsId
	 * @return
	 */
	public boolean deleteGoods(int goodsId) {
		//获取连接
		Connection conn = BaseDao.getConn();
		//设置手动提交
		try {
			conn.setAutoCommit(false);
			//删除商品图片
			BaseDao.execute("delete from goods_picture where goodsId =?", conn, goodsId);
			//删除商品
			BaseDao.execute("delete from Goods where goodsId=?", conn, goodsId);
			//提交
			conn.commit();
			return true;
		} catch (SQLException e) {
			//事务回滚
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭资源
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return  false;
	}

	/**
	 * 修改商品信息
	 * 
	 * @param goods
	 * @return
	 */
	public boolean updateGoods(Goods goods, String goodsStypeName) {

		// 根据小类名，获取小类id
		List<Goods_stype> list = (List<Goods_stype>) BaseDao.select("select * from goods_stype where stypename=?",
				Goods_stype.class, goodsStypeName);
		int stypeId = list.get(0).getSTYPEID();
		// 修改商品
		return BaseDao.execute(
				"update Goods set goodsName=?,stypeId=?,goodsPrice=?,goodsContent=?,goodsStock=? where goodsId=?",
				goods.getGOODSNAME(), stypeId, goods.getGOODSPRICE(), goods.getGOODSCONTENT(), goods.getGOODSSTOCK(),
				goods.getGOODSID()) > 0;
	}

	/**
	 * 获取商品小类
	 * 
	 * @return
	 */
	public List<Goods_stype> getStype() {
		return (List<Goods_stype>) BaseDao.select("select * from goods_stype", Goods_stype.class, null);
	}

	/**
	 * 增加商品（通过类型名获取获取小类型）
	 * 
	 * @param stypeName
	 * @return
	 */
	public boolean addGoods(Goods goods, String stypeName,String pictureAddress) {
		//获取连接
		Connection conn = BaseDao.getConn();
		//设置手动提交
		try {
			conn.setAutoCommit(false);
			
			// 获取商品小类id
			if (stypeName == null) {
				// 如果商品名为空
				//增加商品
				BaseDao.execute("insert into goods values(null,?,null,?,?,?,null)", conn,goods.getGOODSNAME(),
						goods.getGOODSPRICE(), goods.getGOODSCONTENT(), goods.getGOODSSTOCK()) ;
				
			}else{
				// 否则
				
				List<Goods_stype> list = (List<Goods_stype>) BaseDao.select("select * from goods_stype where stypename=?",
						conn,Goods_stype.class, stypeName);
				int stypeId = list.get(0).getSTYPEID();
				// 增加商品
				BaseDao.execute("insert into goods values(null,?,?,?,?,?,null)",conn, goods.getGOODSNAME(), stypeId,
						goods.getGOODSPRICE(), goods.getGOODSCONTENT(), goods.getGOODSSTOCK());
			}
			//获取该商品的id
			List<Goods> goodsIdList = (List<Goods>) BaseDao.select("select * from goods where goodsName=?", conn, Goods.class, goods.getGOODSNAME());
			int goodsId = goodsIdList.get(0).getGOODSID();
			//增加地址
			BaseDao.execute("insert into goods_picture values(null,?,?)", conn, goodsId,pictureAddress);
			//手动提交
			conn.commit();
			return true;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//回滚
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			//关闭资源
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 商品上架
	 * @param goodsId
	 * @return
	 */
	public boolean upGoods(int goodsId) {
		return BaseDao.execute("update goods set goodsState=1 where goodsId=?", goodsId) > 0;
	}
	
	/**
	 * 商品下架
	 * @param goodsId
	 * @return
	 */
	public boolean downGoods(int goodsId) {
		return BaseDao.execute("update goods set goodsState=0 where goodsId=?", goodsId) > 0;
	}

}

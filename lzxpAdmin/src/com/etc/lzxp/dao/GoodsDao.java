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
	 * ��ʾ��Ʒ������Ϣ
	 * 
	 * @return
	 */
	public List<GoodsStypePicture> getAllGoods() {
		return (List<GoodsStypePicture>) BaseDao.select(
				"select * from goods,goods_stype,goods_picture where goods.stypeid=goods_stype.stypeid and goods_picture.goodsid=goods.goodsid", GoodsStypePicture.class, null);
	}

	/**
	 * ͨ��ID����
	 * 
	 * @param goodsId
	 * @return
	 */
	public List<Goods> getGoodsBygoodsId(int goodsId) {
		return (List<Goods>) BaseDao.select("select * from Goods where goodsId like ?", Goods.class, goodsId);
	}

	/**
	 * ������ƷIDɾ��
	 * 
	 * @param goodsId
	 * @return
	 */
	public boolean deleteGoods(int goodsId) {
		//��ȡ����
		Connection conn = BaseDao.getConn();
		//�����ֶ��ύ
		try {
			conn.setAutoCommit(false);
			//ɾ����ƷͼƬ
			BaseDao.execute("delete from goods_picture where goodsId =?", conn, goodsId);
			//ɾ����Ʒ
			BaseDao.execute("delete from Goods where goodsId=?", conn, goodsId);
			//�ύ
			conn.commit();
			return true;
		} catch (SQLException e) {
			//����ع�
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//�ر���Դ
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
	 * �޸���Ʒ��Ϣ
	 * 
	 * @param goods
	 * @return
	 */
	public boolean updateGoods(Goods goods, String goodsStypeName) {

		// ����С��������ȡС��id
		List<Goods_stype> list = (List<Goods_stype>) BaseDao.select("select * from goods_stype where stypename=?",
				Goods_stype.class, goodsStypeName);
		int stypeId = list.get(0).getSTYPEID();
		// �޸���Ʒ
		return BaseDao.execute(
				"update Goods set goodsName=?,stypeId=?,goodsPrice=?,goodsContent=?,goodsStock=? where goodsId=?",
				goods.getGOODSNAME(), stypeId, goods.getGOODSPRICE(), goods.getGOODSCONTENT(), goods.getGOODSSTOCK(),
				goods.getGOODSID()) > 0;
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
	 * ������Ʒ��ͨ����������ȡ��ȡС���ͣ�
	 * 
	 * @param stypeName
	 * @return
	 */
	public boolean addGoods(Goods goods, String stypeName,String pictureAddress) {
		//��ȡ����
		Connection conn = BaseDao.getConn();
		//�����ֶ��ύ
		try {
			conn.setAutoCommit(false);
			
			// ��ȡ��ƷС��id
			if (stypeName == null) {
				// �����Ʒ��Ϊ��
				//������Ʒ
				BaseDao.execute("insert into goods values(null,?,null,?,?,?,null)", conn,goods.getGOODSNAME(),
						goods.getGOODSPRICE(), goods.getGOODSCONTENT(), goods.getGOODSSTOCK()) ;
				
			}else{
				// ����
				
				List<Goods_stype> list = (List<Goods_stype>) BaseDao.select("select * from goods_stype where stypename=?",
						conn,Goods_stype.class, stypeName);
				int stypeId = list.get(0).getSTYPEID();
				// ������Ʒ
				BaseDao.execute("insert into goods values(null,?,?,?,?,?,null)",conn, goods.getGOODSNAME(), stypeId,
						goods.getGOODSPRICE(), goods.getGOODSCONTENT(), goods.getGOODSSTOCK());
			}
			//��ȡ����Ʒ��id
			List<Goods> goodsIdList = (List<Goods>) BaseDao.select("select * from goods where goodsName=?", conn, Goods.class, goods.getGOODSNAME());
			int goodsId = goodsIdList.get(0).getGOODSID();
			//���ӵ�ַ
			BaseDao.execute("insert into goods_picture values(null,?,?)", conn, goodsId,pictureAddress);
			//�ֶ��ύ
			conn.commit();
			return true;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//�ع�
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			//�ر���Դ
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
	 * ��Ʒ�ϼ�
	 * @param goodsId
	 * @return
	 */
	public boolean upGoods(int goodsId) {
		return BaseDao.execute("update goods set goodsState=1 where goodsId=?", goodsId) > 0;
	}
	
	/**
	 * ��Ʒ�¼�
	 * @param goodsId
	 * @return
	 */
	public boolean downGoods(int goodsId) {
		return BaseDao.execute("update goods set goodsState=0 where goodsId=?", goodsId) > 0;
	}

}

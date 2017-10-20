package com.etc.lzxp.dao;

import java.util.List;

import com.etc.lzxp.entity.Seller_message;
import com.etc.util.BaseDao;

/**
 * @author Administrator
 *��Ϣ��ʾdao
 */
public class SellerMessageDao {
	
	/**
	 * �鿴��Ϣ�����Ƿ�������Ϊ1��δ�Ӷ�����������
	 * @return true �� false ��
	 */
	public boolean hasOrderMessage(){
		//��ȡ��Ϣ����Ϊ1(��δ�Ӷ���)������
		 List<Seller_message> list = (List<Seller_message>) BaseDao.select("select * from seller_message where messagecontent=1", Seller_message.class, null);
	
		 if (list.size()==0) {
			//���û��δ�Ӷ���
			 return false;
		}
		 return true;
	}
	
	/**
	 * ȡ����Ϣ����
	 * @return
	 */
	public boolean cancelMessage(){
		return BaseDao.execute("update seller_message set messagecontent=0 where messageid=1", null)>0;
	}
}

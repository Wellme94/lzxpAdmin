package com.etc.lzxp.service;

import com.etc.lzxp.dao.SellerMessageDao;

/**
 * @author Administrator
 *��Ϣ service
 */
public class SellerMessageService {
	 
	SellerMessageDao smd = new SellerMessageDao();
	/**
	 * �鿴��Ϣ�����Ƿ�������Ϊ1��δ�Ӷ�����������
	 * @return true �� false ��
	 */
	public boolean hasOrderMessage(){
		return smd.hasOrderMessage();
	}
	
	/**
	 * ȡ����Ϣ����
	 * @return
	 */
	public boolean cancelMessage(){
		return smd.cancelMessage();
	}
}

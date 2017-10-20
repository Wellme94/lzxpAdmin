package com.etc.lzxp.entity;

import java.io.Serializable;

public class GoodsSort implements Serializable{

	/**
	 * ��Ʒ����ʵ����
	 */
	private static final long serialVersionUID = 1L;
	
	private int LTYPEID;//����ID
	private String LTYPENAME;//������
	private int STYPEID;//С��ID
	private String STYPENAME;//С����
	
	public GoodsSort(int lTYPEID, String lTYPENAME, int sTYPEID, String sTYPENAME) {
		super();
		LTYPEID = lTYPEID;
		LTYPENAME = lTYPENAME;
		STYPEID = sTYPEID;
		STYPENAME = sTYPENAME;
	}
	
	public GoodsSort() {
		// TODO Auto-generated constructor stub
	}

	public int getLTYPEID() {
		return LTYPEID;
	}

	public void setLTYPEID(int lTYPEID) {
		LTYPEID = lTYPEID;
	}

	public String getLTYPENAME() {
		return LTYPENAME;
	}

	public void setLTYPENAME(String lTYPENAME) {
		LTYPENAME = lTYPENAME;
	}

	public int getSTYPEID() {
		return STYPEID;
	}

	public void setSTYPEID(int sTYPEID) {
		STYPEID = sTYPEID;
	}

	public String getSTYPENAME() {
		return STYPENAME;
	}

	public void setSTYPENAME(String sTYPENAME) {
		STYPENAME = sTYPENAME;
	}

	@Override
	public String toString() {
		return "GoodsSort [LTYPEID=" + LTYPEID + ", LTYPENAME=" + LTYPENAME + ", STYPEID=" + STYPEID + ", STYPENAME="
				+ STYPENAME + "]";
	}
	
}

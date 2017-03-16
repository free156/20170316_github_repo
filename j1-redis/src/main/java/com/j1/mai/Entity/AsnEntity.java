package com.j1.mai.Entity;

import java.io.Serializable;
import java.util.Date;

public class AsnEntity implements Serializable {
	private static final long serialVersionUID = -3999312807539224904L;
	private long asnId;
	private long pdsId;
	private String buyerPlantCode;
	private String supplierPlantCode;
	private String asnNo;
	private String invoiceNo;
	private Date asnPublishDate;
	private Date createDate;
	private String createBy;
	private String status;
	private Date deliveryDate;

	public long getAsnId() {
		return asnId;
	}

	public void setAsnId(long asnId) {
		this.asnId = asnId;
	}

	public long getPdsId() {
		return pdsId;
	}

	public void setPdsId(long pdsId) {
		this.pdsId = pdsId;
	}

	public String getBuyerPlantCode() {
		return buyerPlantCode;
	}

	public void setBuyerPlantCode(String buyerPlantCode) {
		this.buyerPlantCode = buyerPlantCode;
	}

	public String getSupplierPlantCode() {
		return supplierPlantCode;
	}

	public void setSupplierPlantCode(String supplierPlantCode) {
		this.supplierPlantCode = supplierPlantCode;
	}

	public String getAsnNo() {
		return asnNo;
	}

	public void setAsnNo(String asnNo) {
		this.asnNo = asnNo;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Date getAsnPublishDate() {
		return asnPublishDate;
	}

	public void setAsnPublishDate(Date asnPublishDate) {
		this.asnPublishDate = asnPublishDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
}

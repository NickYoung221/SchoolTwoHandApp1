package com.school.twohand.entity;

import java.sql.Timestamp;
import java.util.List;

public class Goods {
	private Integer goodsId;
	private ClassTbl goodsClass;
	private User goodsUser;
	private AmoyCircle goodsAmoyCircle;
	private String goodsTitle;
	private String goodsDescribe;
	private Float goodsPrice;
	private Timestamp goodsReleaseTime;
	private Integer goodsState;
	private Byte goodsAuction;
	private List<GoodsImage> goodsImages;
	private List<LikeTbl> goodsLikes;
	private List<MessageBoard> goodsMessageBoards;
	private Integer goodsPV;
	private String goodsUserSchoolName;              //发布该商品的用户的学校名
	private Integer likeSum;
	private Integer messageSum;
	public Goods(User goodsUser, String goodsDescribe, Float goodsPrice, Timestamp goodsReleaseTime,
				 List<GoodsImage> goodsImages, Integer likeSum, Integer messageSum) {
		super();
		this.goodsUser = goodsUser;
		this.goodsDescribe = goodsDescribe;
		this.goodsPrice = goodsPrice;
		this.goodsReleaseTime = goodsReleaseTime;
		this.goodsImages = goodsImages;
		this.likeSum = likeSum;
		this.messageSum = messageSum;
	}

	public Goods(Integer goodsId, String goodsTitle, Float goodsPrice) {
		super();
		this.goodsId = goodsId;
		this.goodsTitle = goodsTitle;
		this.goodsPrice = goodsPrice;
	}

	public Integer getLikeSum() {
		return likeSum;
	}

	public void setLikeSum(Integer likeSum) {
		this.likeSum = likeSum;
	}

	public Integer getMessageSum() {
		return messageSum;
	}

	public void setMessageSum(Integer messageSum) {
		this.messageSum = messageSum;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public ClassTbl getGoodsClass() {
		return goodsClass;
	}

	public void setGoodsClass(ClassTbl goodsClass) {
		this.goodsClass = goodsClass;
	}

	public User getGoodsUser() {
		return goodsUser;
	}

	public void setGoodsUser(User goodsUser) {
		this.goodsUser = goodsUser;
	}

	public AmoyCircle getGoodsAmoyCircle() {
		return goodsAmoyCircle;
	}

	public void setGoodsAmoyCircle(AmoyCircle goodsAmoyCircle) {
		this.goodsAmoyCircle = goodsAmoyCircle;
	}

	public String getGoodsTitle() {
		return goodsTitle;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}

	public String getGoodsDescribe() {
		return goodsDescribe;
	}

	public void setGoodsDescribe(String goodsDescribe) {
		this.goodsDescribe = goodsDescribe;
	}

	public Float getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Float goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public Timestamp getGoodsReleaseTime() {
		return goodsReleaseTime;
	}

	public void setGoodsReleaseTime(Timestamp goodsReleaseTime) {
		this.goodsReleaseTime = goodsReleaseTime;
	}

	public Byte getGoodsAuction() {
		return goodsAuction;
	}

	public void setGoodsAuction(Byte goodsAuction) {
		this.goodsAuction = goodsAuction;
	}

	public List<GoodsImage> getGoodsImages() {
		return goodsImages;
	}

	public void setGoodsImages(List<GoodsImage> goodsImages) {
		this.goodsImages = goodsImages;
	}

	public List<LikeTbl> getGoodsLikes() {
		return goodsLikes;
	}

	public void setGoodsLikes(List<LikeTbl> goodsLikes) {
		this.goodsLikes = goodsLikes;
	}

	public List<MessageBoard> getGoodsMessageBoards() {
		return goodsMessageBoards;
	}

	public void setGoodsMessageBoards(List<MessageBoard> goodsMessageBoards) {
		this.goodsMessageBoards = goodsMessageBoards;
	}

	public Integer getGoodsPV() {
		return goodsPV;
	}

	public void setGoodsPV(Integer goodsPV) {
		this.goodsPV = goodsPV;
	}

	public Integer getGoodsState() {
		return goodsState;
	}

	public void setGoodsState(Integer goodsState) {
		this.goodsState = goodsState;
	}

	public String getGoodsUserSchoolName() {
		return goodsUserSchoolName;
	}

	public void setGoodsUserSchoolName(String goodsUserSchoolName) {
		this.goodsUserSchoolName = goodsUserSchoolName;
	}

	public Goods(Integer goodsId, ClassTbl goodsClass, User goodsUser, AmoyCircle goodsAmoyCircle, String goodsTitle,
				 String goodsDescribe, Float goodsPrice, Timestamp goodsReleaseTime, Integer goodsState, Byte goodsAuction, List<GoodsImage> goodsImages,
				 List<LikeTbl> goodsLikes, List<MessageBoard> goodsMessageBoards, Integer goodsPV, String goodsUserSchoolName) {
		this.goodsId = goodsId;
		this.goodsClass = goodsClass;
		this.goodsUser = goodsUser;
		this.goodsAmoyCircle = goodsAmoyCircle;
		this.goodsTitle = goodsTitle;
		this.goodsDescribe = goodsDescribe;
		this.goodsPrice = goodsPrice;
		this.goodsReleaseTime = goodsReleaseTime;
		this.goodsState = goodsState;
		this.goodsAuction = goodsAuction;
		this.goodsImages = goodsImages;
		this.goodsLikes = goodsLikes;
		this.goodsMessageBoards = goodsMessageBoards;
		this.goodsPV = goodsPV;
		this.goodsUserSchoolName = goodsUserSchoolName;
	}

	public Goods() {
		super();
	}

	
	
	
	
}

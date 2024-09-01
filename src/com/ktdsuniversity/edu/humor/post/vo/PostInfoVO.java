package com.ktdsuniversity.edu.humor.post.vo;

import java.util.List;

import com.ktdsuniversity.edu.humor.attachment.vo.AttachmentInfoVO;
import com.ktdsuniversity.edu.humor.comment.vo.CommentInfoVO;

public class PostInfoVO {

	private int pstId;
	private String postTtl;
	private String pstTm;
	private String pstCntnt;
	private String pstSrce;
	
	private List<AttachmentInfoVO> attachmentList;
	private List<CommentInfoVO> commentList;
	
	public List<AttachmentInfoVO> getAttachmentList() {
		return attachmentList;
	}
	public void setAttachmentList(List<AttachmentInfoVO> attachmentList) {
		this.attachmentList = attachmentList;
	}
	public List<CommentInfoVO> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<CommentInfoVO> commentList) {
		this.commentList = commentList;
	}
	public int getPstId() {
		return pstId;
	}
	public void setPstId(int pstId) {
		this.pstId = pstId;
	}
	public String getPostTtl() {
		return postTtl;
	}
	public void setPostTtl(String postTtl) {
		this.postTtl = postTtl;
	}
	public String getPstTm() {
		return pstTm;
	}
	public void setPstTm(String pstTm) {
		this.pstTm = pstTm;
	}
	
	public String getPstCntnt() {
		return pstCntnt;
	}
	
	public void setPstCntnt(String pstCntnt) {
		this.pstCntnt = pstCntnt;
	}
	public String getPstSrce() {
		return pstSrce;
	}
	public void setPstSrce(String pstSrce) {
		this.pstSrce = pstSrce;
	}
	
	
}

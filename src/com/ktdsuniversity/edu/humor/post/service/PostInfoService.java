package com.ktdsuniversity.edu.humor.post.service;

import java.util.List;

import com.ktdsuniversity.edu.humor.attachment.dao.AttachmentInfoDao;
import com.ktdsuniversity.edu.humor.attachment.vo.AttachmentInfoVO;
import com.ktdsuniversity.edu.humor.helper.DataAccessHelper;
import com.ktdsuniversity.edu.humor.post.dao.PostInfoDao;
import com.ktdsuniversity.edu.humor.post.vo.PostInfoVO;

public class PostInfoService {
	
	public void readAllPostsAndComments() {
		DataAccessHelper dataAccessHelper = null;
		
		try {
			dataAccessHelper = new DataAccessHelper();
			PostInfoDao postInfoDao = new PostInfoDao();
			List<PostInfoVO> postList = postInfoDao.selectPost(dataAccessHelper);
			
			postList.forEach(post -> {
				System.out.println("게시글 아이디: " + post.getPstId());
				System.out.println("게시글 제목: " + post.getPostTtl());
				System.out.println("게시글 작성날짜: " + post.getPstTm());
				System.out.println("게시글 내용: " + post.getPstCntnt());
				System.out.println("게시글 출처: " + post.getPstSrce());
				System.out.println("첨부파일 주소: " + post.getAttachmentList());
				System.out.println("댓글 아이디: " + post.getCommentList());
			});
		}catch(RuntimeException re) {
			System.out.println(re.getMessage());
		}
	}

	public void createNewPost(PostInfoVO postInfoVO, List<AttachmentInfoVO> attachmentList) {
		
		DataAccessHelper dataAccessHelper = null;
		
		try {
			dataAccessHelper = new DataAccessHelper();
			
			PostInfoDao postInfoDao = new PostInfoDao();
			postInfoDao.insertNewPost(postInfoVO, dataAccessHelper);
			
			if(attachmentList != null) {
				for(AttachmentInfoVO attachmentInfoVO : attachmentList) {
					AttachmentInfoDao attachmentInfoDao = new AttachmentInfoDao();
					attachmentInfoDao.insertNewAttachment(attachmentInfoVO, postInfoVO.getPstId(), dataAccessHelper);
				}
			}
			
			dataAccessHelper.commit();
		}catch(RuntimeException re) {
			re.printStackTrace();
			System.out.println(re.getMessage());
			if(dataAccessHelper != null) {
				dataAccessHelper.rollback();
			}
		}finally {
			if(dataAccessHelper != null) {
				dataAccessHelper.close();
			}
		}
	}
}

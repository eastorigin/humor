package com.ktdsuniversity.edu.humor;

import java.util.ArrayList;
import java.util.List;

import com.ktdsuniversity.edu.humor.attachment.vo.AttachmentInfoVO;
import com.ktdsuniversity.edu.humor.comment.service.CommentInfoService;
import com.ktdsuniversity.edu.humor.comment.vo.CommentInfoVO;
import com.ktdsuniversity.edu.humor.post.service.PostInfoService;
import com.ktdsuniversity.edu.humor.post.vo.PostInfoVO;

public class Humor {
	
	public static void selectAllPostsandComments() {
		PostInfoService postInfoService = new PostInfoService();
		postInfoService.readAllPostsAndComments();
	}
	
	public static void insertNewPost() {
		PostInfoService postInfoService = new PostInfoService();
		
		PostInfoVO postInfoVO = new PostInfoVO();
		postInfoVO.setPostTtl("게시글 테스트");
		postInfoVO.setPstTm("2024-09-01");
		postInfoVO.setPstCntnt("임시 내용");
		postInfoVO.setPstSrce("내 머리 속");
		
		List<AttachmentInfoVO> attachmentList = new ArrayList<>();
		AttachmentInfoVO attachmentInfoVO = new AttachmentInfoVO();
		attachmentInfoVO.setTtchmntUrl("https://temp.temp");
		attachmentList.add(attachmentInfoVO);
		
		postInfoService.createNewPost(postInfoVO, attachmentList);
	}
	
	public static void insertNewComment() {
		CommentInfoService commentInfoService = new CommentInfoService();
		
		CommentInfoVO commentInfoVO = new CommentInfoVO();
		commentInfoVO.setCmmntThr("아무개");
		commentInfoVO.setCmmntTm("2024-09-01");
		commentInfoVO.setCmmntCntnt("댓글 테스트");
		commentInfoVO.setPstId(4);
		commentInfoVO.setPprCmmntId(null);
		
		commentInfoService.createNewComment(commentInfoVO);
	}
	
	public static void main(String[] args) {
		// insertNewPost();
		// insertNewComment();
		selectAllPostsandComments();
	}
}

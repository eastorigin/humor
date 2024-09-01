package com.ktdsuniversity.edu.humor.post.dao;

import java.util.ArrayList;
import java.util.List;

import com.ktdsuniversity.edu.humor.attachment.vo.AttachmentInfoVO;
import com.ktdsuniversity.edu.humor.comment.vo.CommentInfoVO;
import com.ktdsuniversity.edu.humor.helper.DataAccessHelper;
import com.ktdsuniversity.edu.humor.helper.SQLType;
import com.ktdsuniversity.edu.humor.post.vo.PostInfoVO;

public class PostInfoDao {
	
	public List<PostInfoVO> selectPost(DataAccessHelper dataAccessHelper) {
		List<PostInfoVO> postList = new ArrayList<>();
		
		StringBuffer query = new StringBuffer();
		
		query.append(" SELECT pi.PST_ID               								");
		query.append("  	 , pi.PST_TTL             								");
		query.append("  	 , TO_CHAR(pi.PST_TM,'YYYY-MM-DD') PST_TM              ");
		query.append("  	 , pi.PST_CNTNT           								");
		query.append("  	 , pi.PST_SRCE            								");
		query.append("  	 , pa.TTCHMNT_URL         								");
		query.append("  	 , ci.CMMNT_ID            								");
		query.append("  	 , ci.CMMNT_THR           								");
		query.append("  	 , TO_CHAR(ci.CMMNT_TM,'YYYY-MM-DD') CMMNT_TM           								");
		query.append("  	 , TO_CHAR(ci.CMMNT_DT_TM,'YYYY-MM-DD') CMMNT_DT_TM        								");
		query.append("  	 , ci.CMMNT_CNTNT         								");
		query.append("   FROM POST_INFO pi            								");
		query.append("   JOIN POST_ATTACHMENT pa      								");
		query.append("   	ON pi.PST_ID = pa.PST_ID  								");
		query.append("   JOIN COMMENT_INFO ci         								");
		query.append("   	ON pi.PST_ID = ci.PST_ID  								");
		
		dataAccessHelper.preparedStatement(query.toString(), null);
		dataAccessHelper.executeQuery(SQLType.SELECT, rs -> {
			int pstId = rs.getInt("PST_ID");
			String postTtl = rs.getString("PST_TTL");
			String pstTm = rs.getString("PST_TM");
			String pstCntnt = rs.getString("PST_CNTNT");
			String pstSrce = rs.getString("PST_SRCE");
			String ttchmntUrl = rs.getString("TTCHMNT_URL");
			int cmmntId = rs.getInt("CMMNT_ID");
			String cmmntThr = rs.getString("CMMNT_THR");
			String cmmntTm = rs.getString("CMMNT_TM");
			String cmmntDtTm = rs.getString("CMMNT_DT_TM");
			String cmmntCntnt = rs.getString("CMMNT_CNTNT");
			
			PostInfoVO postInfoVO = new PostInfoVO();
			postList.add(postInfoVO);
			postInfoVO.setPstId(pstId);
			postInfoVO.setPostTtl(postTtl);
			postInfoVO.setPstTm(pstTm);
			postInfoVO.setPstCntnt(pstCntnt);
			postInfoVO.setPstSrce(pstSrce);
			
			List<AttachmentInfoVO> attachmentList = new ArrayList<>();
			postInfoVO.setAttachmentList(attachmentList);
			AttachmentInfoVO attachmentInfoVO = new AttachmentInfoVO();
			attachmentInfoVO.setTtchmntUrl(ttchmntUrl);
			
			List<CommentInfoVO> commentList = new ArrayList<>();
			postInfoVO.setCommentList(commentList);
			CommentInfoVO commentInfoVO = new CommentInfoVO();
			commentInfoVO.setCmmntId(cmmntId);
			commentInfoVO.setCmmntThr(cmmntThr);
			commentInfoVO.setCmmntTm(cmmntTm);
			commentInfoVO.setCmmntDtTm(cmmntDtTm);
			commentInfoVO.setCmmntCntnt(cmmntCntnt);
		});
		
		return postList;
	}

	public void insertNewPost(PostInfoVO newPostData, DataAccessHelper dataAccessHelper) {
		
		String newPkValueQuery = "SELECT LPAD(POST_INFO_PK_SEQ.NEXTVAL, 5, '0') AS PST_ID FROM DUAL";
		dataAccessHelper.preparedStatement(newPkValueQuery, null);
		dataAccessHelper.executeQuery(SQLType.SELECT, rs -> {
			int newPk = rs.getInt("PST_ID");
			newPostData.setPstId(newPk);
		});
		
		StringBuffer query = new StringBuffer();
		query.append(" INSERT INTO POST_INFO  						");
		query.append(" (PST_ID                						");
		query.append(" , PST_TTL              						");
		query.append(" , PST_TM               						");
		query.append(" , PST_CNTNT            						");
		query.append(" , PST_SRCE)            						");
		query.append(" VALUES                 						");
		query.append(" (?                     						");
		query.append(" , ?                   						");
		query.append(" , TO_DATE(?,'YYYY-MM-DD')                   ");
		query.append(" , ?                   						");
		query.append(" , ?)                  						");
		
		dataAccessHelper.preparedStatement(query.toString(), pstmt -> {
			pstmt.setInt(1, newPostData.getPstId());
			pstmt.setString(2, newPostData.getPostTtl());
			pstmt.setString(3, newPostData.getPstTm());
			pstmt.setString(4, newPostData.getPstCntnt());
			pstmt.setString(5, newPostData.getPstSrce());
		});
		
		dataAccessHelper.executeQuery(SQLType.INSERT, null);
	}
}

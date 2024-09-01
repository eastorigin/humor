package com.ktdsuniversity.edu.humor.comment.dao;

import com.ktdsuniversity.edu.humor.comment.vo.CommentInfoVO;
import com.ktdsuniversity.edu.humor.helper.DataAccessHelper;
import com.ktdsuniversity.edu.humor.helper.SQLType;

public class CommentInfoDao {
	
	public void insertComment(CommentInfoVO newCommentData, int pstId, Integer pprCmmntId, DataAccessHelper dataAccessHelper) {
		
		String newPkValueQuery = "SELECT LPAD(COMMENT_INFO_PK_SEQ.NEXTVAL, 5, '0') COMMENT_INFO_PK FROM DUAL";
		dataAccessHelper.preparedStatement(newPkValueQuery, null);
		dataAccessHelper.executeQuery(SQLType.SELECT, rs -> {
			int newPk = rs.getInt("COMMENT_INFO_PK");
			newCommentData.setCmmntId(newPk);
		});
		
		StringBuffer query = new StringBuffer();
		query.append(" INSERT INTO COMMENT_INFO 					");
		query.append(" (CMMNT_ID                					");
		query.append(" , PST_ID                 					");
		query.append(" , PPR_CMMNT_ID           					");
		query.append(" , CMMNT_THR              					");
		query.append(" , CMMNT_TM               					");
		query.append(" , CMMNT_DT_TM            					");
		query.append(" , CMMNT_CNTNT)           					");
		query.append(" VALUES                   					");
		query.append(" ( ?                       					");
		query.append(" , ?                      					");
		query.append(" , ?                      					");
		query.append(" , ?                     						");
		query.append(" , TO_DATE(?,'YYYY-MM-DD')                     ");
		query.append(" , TO_DATE(?,'YYYY-MM-DD')                     ");
		query.append(" , ?)                    						");
		
		dataAccessHelper.preparedStatement(query.toString(), pstmt -> {
			pstmt.setInt(1, newCommentData.getCmmntId());
			pstmt.setInt(2, pstId);
			pstmt.setObject(3, pprCmmntId, java.sql.Types.INTEGER);
			pstmt.setString(4, newCommentData.getCmmntThr());
			pstmt.setString(5, newCommentData.getCmmntTm());
			pstmt.setString(6, newCommentData.getCmmntDtTm());
			pstmt.setString(7, newCommentData.getCmmntCntnt());
		});
		
		dataAccessHelper.executeQuery(SQLType.INSERT, null);
	}
}

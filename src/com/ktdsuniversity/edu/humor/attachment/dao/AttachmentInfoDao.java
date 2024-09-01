package com.ktdsuniversity.edu.humor.attachment.dao;

import com.ktdsuniversity.edu.humor.attachment.vo.AttachmentInfoVO;
import com.ktdsuniversity.edu.humor.helper.DataAccessHelper;
import com.ktdsuniversity.edu.humor.helper.SQLType;

public class AttachmentInfoDao {

	public void insertNewAttachment(AttachmentInfoVO newAttachmentData, int pstId, DataAccessHelper dataAccessHelper) {
		String newPkValueQuery = "SELECT LPAD(POST_ATTACHMENT_PK_SEQ.NEXTVAL, 5, '0') POST_ATTACHMENT_PK FROM DUAL";
		dataAccessHelper.preparedStatement(newPkValueQuery, null);
		dataAccessHelper.executeQuery(SQLType.SELECT, rs -> {
			int newPk = rs.getInt("POST_ATTACHMENT_PK");
			newAttachmentData.setTtchmntId(newPk);
		});
		
		StringBuffer query = new StringBuffer();
		query.append(" INSERT INTO POST_ATTACHMENT ");
		query.append(" (TTCHMNT_ID                   ");
		query.append(" , PST_ID                      ");
		query.append(" , TTCHMNT_URL)                ");
		query.append(" VALUES                        ");
		query.append(" (?                            ");
		query.append(" , ?                           ");
		query.append(" , ?)                         ");
		
		dataAccessHelper.preparedStatement(query.toString(), pstmt -> {
			pstmt.setInt(1, newAttachmentData.getTtchmntId());
			pstmt.setInt(2, pstId);
			pstmt.setString(3, newAttachmentData.getTtchmntUrl());
		});
		dataAccessHelper.executeQuery(SQLType.INSERT, null);
	}
}

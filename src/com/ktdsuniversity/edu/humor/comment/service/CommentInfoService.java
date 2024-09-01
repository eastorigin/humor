package com.ktdsuniversity.edu.humor.comment.service;

import com.ktdsuniversity.edu.humor.comment.dao.CommentInfoDao;
import com.ktdsuniversity.edu.humor.comment.vo.CommentInfoVO;
import com.ktdsuniversity.edu.humor.helper.DataAccessHelper;

public class CommentInfoService {

	public void createNewComment(CommentInfoVO commentInfoVO) {
		
		DataAccessHelper dataAccessHelper = null;
		
		try {
			dataAccessHelper = new DataAccessHelper();
			
			CommentInfoDao commentInfoDao = new CommentInfoDao();
			
			Integer pprCmmntId = commentInfoVO.getPprCmmntId();

			commentInfoDao.insertComment(commentInfoVO, commentInfoVO.getPstId(), pprCmmntId, dataAccessHelper);
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

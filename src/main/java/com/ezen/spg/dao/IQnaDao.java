package com.ezen.spg.dao;
    
import java.util.ArrayList;



import org.apache.ibatis.annotations.Mapper;

import com.ezen.spg.dto.QnaVO;

@Mapper
public interface IQnaDao {

	ArrayList<QnaVO> listQna(String id);
	void insertQna(QnaVO qnavo);
	QnaVO getQna(int qseq);
	void deleteQna(int qnaseq);
	void updateQna(QnaVO qnavo);
	void updateQnaReply(QnaVO qvo);
  
}

package com.ezen.spg.service;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spg.dao.IQnaDao;
import com.ezen.spg.dto.QnaVO;
  
@Service
public class QnaService {
	
	@Autowired
	IQnaDao qdao;

	public ArrayList<QnaVO> listQna(String id) {
		return qdao.listQna(id);
	}

	public void insertQna(QnaVO qnavo) {
		qdao.insertQna(qnavo);
		
	}

	public QnaVO getQna(int qseq) {
		return qdao.getQna(qseq);
	}

	public void deleteQna(int qnaseq) {
	qdao.deleteQna(qnaseq);
		
	}

	public void updateQna(QnaVO qnavo) {
		qdao.updateQna(qnavo);
		
	}

	public void updateQnaReply(QnaVO qvo) {
		qdao.updateQnaReply(qvo);
		
	}
	
}

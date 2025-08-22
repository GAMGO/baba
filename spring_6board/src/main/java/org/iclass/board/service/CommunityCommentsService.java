package org.iclass.board.service;

import java.util.List;
import org.iclass.board.dto.CommunityCommentDTO;
import org.iclass.board.mapper.CommunityCommentsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Service
public class CommunityCommentsService {
    private CommunityCommentsMapper mapper;

    // 추가+댓글수 더하기
    @Transactional
    public int reply(CommunityCommentDTO dto) {
        int result = mapper.insert(dto);
        mapper.updateCommentCount(dto.getMref());
        return result;
    }

    // 삭제+댓글수 빼기
    @Transactional
    public int delete(int idx, int mref) {
        int result = mapper.delete(idx);
        mapper.updateCommentCount(mref);
        return result;
    }

    // 해당글 댓글 리스트 불러오기
    public List<CommunityCommentDTO> clist(int mref) {
        return mapper.selectCommentList(mref);
    }
}

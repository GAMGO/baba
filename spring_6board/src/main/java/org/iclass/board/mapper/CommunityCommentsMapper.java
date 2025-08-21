package org.iclass.board.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.iclass.board.dto.CommunityCommentDTO;

@Mapper
public interface CommunityCommentsMapper {
    CommunityCommentDTO selectOneByIdx(long idx);
    CommunityCommentDTO selectCommentList (int mref);
    int insert(CommunityCommentDTO dto);
    int delete(long idx);
    int selectCommentCount (int mref);
    int updateCommentCount (int mref);
}

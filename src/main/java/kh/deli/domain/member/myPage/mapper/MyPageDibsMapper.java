package kh.deli.domain.member.myPage.mapper;

import kh.deli.domain.member.myPage.dto.MyPageDibsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MyPageDibsMapper {

    public List<MyPageDibsDTO> select(@Param("acc_seq") int acc_seq);

    public void insertDibs(@Param("acc_seq") int acc_seq, @Param("store_seq") int store_seq);

    public void deleteDibs(@Param("acc_seq") int acc_seq, @Param("store_seq") int store_seq);

    public Integer isExistDibs(@Param("acc_seq") int acc_seq, @Param("store_seq")int store_seq);
}

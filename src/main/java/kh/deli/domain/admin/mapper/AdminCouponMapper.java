package kh.deli.domain.admin.mapper;

import kh.deli.global.entity.CouponDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdminCouponMapper {

    @Insert("INSERT INTO COUPON VALUES (" +
            "CP_SEQ.NEXTVAL, " +
            "#{cp.cp_code}, " +
            "#{cp.cp_name}, " +
            "#{cp.cp_content}, " +
            "#{cp.cp_discount}, " +
            "#{cp.cp_type}, " +
            "#{cp.cp_period})")
    void put(@Param("cp") CouponDTO coupon);

    @Select("SELECT COUNT(CP_CODE) FROM COUPON WHERE CP_CODE = #{code}")
    int getCpCodeByCpCode(@Param("code") String cpCode);

}
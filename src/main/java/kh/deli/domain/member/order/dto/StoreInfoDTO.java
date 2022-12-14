package kh.deli.domain.member.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreInfoDTO {
    private String store_name;
    private String store_deli_time;
    private int order_seq;
    private String order_date;
    private String store_phone;
    private String store_add_detail1;
    private String store_add_detail2;
}

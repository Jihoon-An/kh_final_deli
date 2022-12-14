package kh.deli.domain.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminReviewDTO {
    private String mem_nick;
    private String rev_content;
    private String store_name;
    private String rev_writedate;
    private String rev_modified_date;
    private int rev_seq;
    private int rev_star;
    private String rev_display;
}



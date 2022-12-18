package kh.deli.domain.member.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreReviewDTO {

    private String mem_nick;
    private String rev_writedate;
    private int rev_star;
    private List<String> rev_sysname;
    private String rev_content;
    private List<String> menu_list;

}
package kh.deli.domain.member.store.service;

import com.google.gson.Gson;
import kh.deli.domain.member.store.mapper.StoreMenuOptionMapper;
import kh.deli.global.entity.MenuOptionDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class StoreMenuOptionService {

    private final StoreMenuOptionMapper optionMapper;
    private final Gson gson;

    public List<MenuOptionDTO> findByMenuSeq(int menuSeq) {
        Optional<List<MenuOptionDTO>> optionalMenuDTO = Optional.ofNullable(optionMapper.findByMenuSeq(menuSeq));
        return optionalMenuDTO.orElse(new ArrayList<>());
    }

    /**
     * @return Map<option_group, options>
     */
    public Map<String, List<MenuOptionDTO>> toMap(List<MenuOptionDTO> options) {

        Map<String, List<MenuOptionDTO>> optionGroupMap = new HashMap<>();

        for (MenuOptionDTO option : options) {

            if (optionGroupMap.containsKey(option.getOption_group())) {
                //이미 그룹이 있으면
                optionGroupMap.get(option.getOption_group()).add(option);
            } else {
                //그룹이 없으면
                List<MenuOptionDTO> newOptionArr = new ArrayList<>();

                newOptionArr.add(option);

                optionGroupMap.put(option.getOption_group(), newOptionArr);
            }
        }

        return optionGroupMap;
    }

    public List<MenuOptionDTO> parseSeqToObjectList(List<Integer> optionSeqList) {

        String optionSeqListStr = gson.toJson(optionSeqList);

        optionSeqListStr = optionSeqListStr.replace("[","(");
        optionSeqListStr = optionSeqListStr.replace("[",")");

        Optional<List<MenuOptionDTO>> options = Optional.ofNullable(optionMapper.getList(optionSeqListStr));
        return options.orElse(new ArrayList<>());
    }
}

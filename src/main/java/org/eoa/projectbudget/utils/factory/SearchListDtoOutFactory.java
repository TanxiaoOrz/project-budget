package org.eoa.projectbudget.utils.factory;

import org.eoa.projectbudget.dto.SearchListDto;
import org.eoa.projectbudget.vo.out.SearchListDtoOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 张骏山
 * @Date: 2024/1/14 20:23
 * @PackageName: org.eoa.projectbudget.utils.factory
 * @ClassName: SearchListDtoOutFactory
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class SearchListDtoOutFactory implements OutFactory<SearchListDto, SearchListDtoOut> {
    @Autowired
    SearchListOutFactory searchListOutFactory;
    @Override
    public SearchListDtoOut out(SearchListDto searchListDto) {
        if (searchListDto == null) {
            return null;
        }
        return new SearchListDtoOut()
                .setSearchListOut(searchListOutFactory.out(searchListDto.getSearchList()))
                .setColumns(searchListDto.getColumns())
                .setSearchListColumns(searchListDto.getSearchListColumns());
    }

    @Override
    public List<SearchListDtoOut> outs(List<? extends SearchListDto> searchListDtos) {
        if (searchListDtos == null) {
            return null;
        }
        return searchListDtos.stream().map(this::out).toList();
    }
}

package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class PaginateServie {
    
    /**
     * 件数とページ当たりの件数から、ページ数を取得する。
     * @param numberOfTotal
     * @param limit
     * @return
     */
    public int getNumberOfpages(int numberOfTotal,int limit){

        int remainder = numberOfTotal % limit;
        if (remainder == 0) {
            return numberOfTotal/limit;
        }else{
            return (numberOfTotal/limit)+1;
        }
    }
    
    /**
     * 表示するページネートを作成
     * @param numberOfPages ページ数
     * @param page 現在のページ
     * @List<String> ページネート
     */
    public Map<String,Integer> getPaginate(int numberOfPages ,int page){
        Map<String,Integer> paginate = new LinkedHashMap<>();
        // if (page > 1) {
        //     paginate.put("<",page-1);
        // }
        paginate.put("1", 1);
        //現在のページ数に合わせて、表示するページの数字を変更する
        int i ;
        if (page<=6) {
            i = 2;
        }else{
            i = page-2;
        }

        int count = 0;
        int countMax = 6;
        //総ページ数でListの数を変更する
        if (numberOfPages<=7) {
            countMax = numberOfPages-2;
        }
        while (count<countMax) {
            paginate.put(String.valueOf(i),i);
            i++;
            count++;
        }
        // if (numberOfPages>page) {
        //     paginate.put(">",page+1);
        // }
        paginate.put(String.valueOf(numberOfPages),numberOfPages);
        if (numberOfPages==1) {
            paginate = new HashMap<>();
            paginate.put("1",1);
        }

        return paginate;
    }

    /**
     * ofsetの値を取得する
     * @param page　現在のページ
     * @param limit １ページ当たりの件数
     * @return int ofset
     */
    public int getOfset(int page,int limit){
       return (page-1)*limit;
    }


}

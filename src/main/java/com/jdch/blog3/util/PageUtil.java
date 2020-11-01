package com.jdch.blog3.util;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 *
 * @author Zoisitesky
 * @date 2020-11-01 19:12
 */
public class PageUtil {
    public static <T> PageBean<T> getPage(Integer pageNow, List<T> allData, Integer pageSize) {
        try {
            if (allData.size() == 0) {
                return null;
            }
            if (pageNow == null || pageNow == 0) {
                pageNow = 1;
            }
            // 总记录数
            int total = allData.size();
            // 总页数
            int pages;
            if (total < pageSize) {
                pages = 1;
            } else {
                if (total % pageSize == 0) {
                    pages = (int) (total / pageSize);
                } else {
                    pages = (int) (total / pageSize + 1);
                }
            }
            // 当前页数据第一个索引
            int currentPage;
            if (pageNow == 1) {
                currentPage = 0;
            } else {
                currentPage = (pageNow - 1) * pageSize;
            }
            // 当前页数据最后一个索引
            int finalRecord;
            // 当前页数据集
            List<T> datas = null;
            if (pageSize >= total) { // 当每页数量大于总记录数
                finalRecord = (int) total;
            } else if (pageNow == pages) { // 当前也是最后一页时
                finalRecord = (int) total;
            } else {
                finalRecord = currentPage + pageSize;
            }
            // 当需要显示的第一条数据索引大于总记录数，或者，需要显示的数据最后一条数据缩影大于总记录数
            if (currentPage > total || finalRecord > total) {
                return null;
            }
            // 当每页显示的数据大于等于总记录数时
            if (pageSize >= total) {
                datas = allData;
            } else {
                datas = new ArrayList<T>(allData.subList(currentPage, finalRecord));
            }
            return new PageBean<T>(total, pages, datas,pageNow);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

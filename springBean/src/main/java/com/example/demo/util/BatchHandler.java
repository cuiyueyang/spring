package com.example.demo.util;

import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>Description: </p>
 * <p>Date: 2021/9/24 11:10</p>
 *
 * @author jin jie
 **/
public class BatchHandler<T> {
    /**
     * @example List<String> resList = Arrays.asList("0", "1", "2", "3", "4", "5", "6",
     *                 "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
     *                 "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
     *                 "28", "29", "30", "31", "32", "33", "34", "35", "36", "37",
     *                 "38", "39", "40", "41", "42", "43", "44", "45", "46", "47",
     *                 "48", "49", "50", "51", "52", "53", "54", "55", "56", "57",
     *                 "58", "59", "60", "61", "62", "63", "64", "65", "66", "67",
     *                 "68", "69", "70", "71", "72", "73", "74", "75", "76", "77",
     *                 "78", "79", "80", "81", "82", "83", "84", "85", "86", "87",
     *                 "88", "89", "90", "91", "92", "93", "94", "95", "96", "97",
     *                 "98","99","100");
     *         resList = new ArrayList<>(resList);
     *         new BatchHandler<String>().batchResolve(resList,10,(items)->{
     *             对分批后的集合的处理
     *             System.out.println("-----"+items);
     *         });
     * @param list
     * @param batchSize
     * @param resolve
     */
    public void batchResolve(List<T> list, int batchSize, CommonResolve<T> resolve) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        for (int i = 0; i < list.size(); i += batchSize) {
            int end = i + batchSize;
            if (end > list.size()) {
                end = list.size();
            }
            List<T> items = list.subList(i, end);
            resolve.resolve(items);
        }
    }

}

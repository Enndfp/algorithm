package com.enndfp.charpter3_array.level2.topic2_5替换空格;

/**
 * 剑指 Offer 替换空格
 *
 * @author Enndfp
 */
public class ReplaceSpace {
    /**
     * 字符串拼接
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sb.append("%20");
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}

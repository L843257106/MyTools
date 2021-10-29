package pers.liuhan.toolkit.util;


import pers.liuhan.toolkit.constant.Punctuation;

import java.util.Map;
import java.util.Objects;

/**
 * @author liuhan19691
 */
public class MapUtil {

    public static void fillMapWithStringArray(Map<String, String> map, String[] lines) {
        if (Objects.isNull(map)) {
            return;
        }
        map.clear();
        if (Objects.isNull(lines) || lines.length == 0) {
            return;
        }
        for (String line : lines) {
            String[] values = line.split(Punctuation.ARROW);
            if (values.length == 2) {
                map.put(values[0], values[1]);
            }
        }
    }
}

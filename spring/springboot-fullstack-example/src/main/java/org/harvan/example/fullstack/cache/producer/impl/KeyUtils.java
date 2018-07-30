package org.harvan.example.fullstack.cache.producer.impl;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0
 */
public class KeyUtils {

    public static String getKey(String prefix, Object... id) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;

        for (Object obj : id) {
            if (first) {
                sb.append(obj);
                first = false;
            } else {
                sb.append(":").append(obj);
            }
        }
        return prefix.concat("::").concat(sb.toString());
    }
}
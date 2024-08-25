package dev.hisoka.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Constants {
    public static Map<Integer,String> TILE_INFO = new HashMap<>();

    public static Set<Integer> COLLISION = new HashSet<>();

    static {
        TILE_INFO.put(0,"/map/0.png");
        TILE_INFO.put(1,"/map/1.png");
    }

    static {
        COLLISION.add(1);
    }

}

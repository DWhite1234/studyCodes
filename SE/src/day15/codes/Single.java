package day15.codes;

import com.sun.org.apache.bcel.internal.generic.RETURN;

public class Single {
    //饿汉式,类似于枚举
    private static Single single = new Single();

    private Single() {}

    public static Single getInstance() {
        return single;
    }
}

class HungrySingle {

    private static HungrySingle instance=null;

    public HungrySingle() {
    }

    public static synchronized HungrySingle getInstance() {
        if (instance == null) {
            instance = new HungrySingle();
        }
        return instance;
    }
}

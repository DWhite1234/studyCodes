import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        HashMap<Object, Object> map = new HashMap<>();
        map.put("1", "libai");
        Set<Object> keySet = map.keySet();
        Iterator<Object> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println(next+"--->"+map.get(next));
        }
    }
}

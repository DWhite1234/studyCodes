package day07.codes;

public class JuXing {
    int length;
    int width;

    public int calArea(int l,int w) {
        return l * w;
    }

    public int calTotalLength(int l,int w) {
        return 2 * (l + w);
    }

    public String showObject(int l,int w) {
        calArea(l, w);
        calTotalLength(l, w);
        return "长:"+l+"宽:"+w+"面积:"+calArea(l, w)+"周长:"+calTotalLength(l,w);
    }
}

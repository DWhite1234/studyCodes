package day05.codes;

public class Test1 {
    public static void main(String[] args) {
        char[] arr = new char[26];
        char b = 'a';
        for (int i = 0; i < arr.length; i++) {
            arr[i] = b;
            b++;
        }
        for (char c : arr) {
            System.out.println(c + "-->" + (char) (c - 32));
        }
    }
}

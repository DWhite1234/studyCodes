package day18.codes;

public class TestArrayListTool {
    public static void main(String[] args) {
        ArrayListTool<String> tool = new ArrayListTool<>();
        tool.add("李白");
        tool.add("李四");

        System.out.println("tool.toString() = " + tool.toString());
    }
}

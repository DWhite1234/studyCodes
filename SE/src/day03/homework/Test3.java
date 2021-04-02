package day03.homework;

import java.util.Scanner;

public class Test3 {
/*
*
* 1. 定义类 Test2
2. 定义 main方法
3. 定义变量折扣 discount，初始化为1, 总价totalPrice的值从键盘输入
4. 判断当`totalPrice >=500` ,discount赋值为0.5
5. 判断当`totalPrice >=400` 且`<500`时,discount赋值为0.6
6. 判断当`totalPrice >=300` 且`<400`时,discount赋值为0.7
7. 判断当`totalPrice >=200` 且`<300`时,discount赋值为0.8
8. 判断当`totalPrice >=0` 且`<200`时,discount赋值为1
9. 判断当`totalPrice<0`时，显示输入有误
10. 输出结果
* */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入商品总价");

        double totalPrice = scanner.nextDouble();

        double count = 1;

        if(totalPrice>0){
            if (totalPrice>=500){
                count = 0.5;
            }else if (totalPrice>=400&&totalPrice<500){
                count = 0.6;
            }else if (totalPrice>=300&&totalPrice<400){
                count = 0.7;
            }else if (totalPrice>=200&&totalPrice<300){
                count = 0.8;
            }
            System.out.println("总价:"+totalPrice);
            System.out.println("折扣:"+count);
            System.out.println("折扣后总价:"+totalPrice*count);
        }else{
            System.out.println("输入有误");
        }
    }
}

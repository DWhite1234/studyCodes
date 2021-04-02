package day16.homework;

public class TestHomework1 {
	public static void main(String[] args) throws InterruptedException {
		int[] a = new int[100];
		for (int i = 0; i < a.length; i++) {
			a[i] = i;
		}
		
		int i=0;
		int count = 0;
		int delNum = 0;//被删掉的数字个数
		while(true) {
			if(a[i]!=-1) {//被删掉的数不再计入count个数
				count++;
			}
			if(count==3) {//隔两个，第三个删掉
				a[i]=-1;//a[i]=-1，表示被删掉的数
				count=0;//count重新计数
				delNum++;//统计已经被删掉几个了
			}
			if(delNum==a.length-1) {//留下最后一个结束删除过程
				break;
			}
			if(++i==a.length) {//如果下标右移已经到头了，要从头开始
				i=0;
			}
		}
		
		for (int j = 0; j < a.length; j++) {
			if(a[j]!=-1) {
				System.out.println("最后一个被删掉的数是：a[j]=" + a[j] + "，它的下标：" + j);
			}
		}
	}
}
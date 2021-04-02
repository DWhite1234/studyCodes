package day16.codes;

public class binarySearch {
    public static void main(String[] args) {

        //二分查找法
        /*
          思路:
            设置一个起始点(startIndex),一个终止点(endIndex),还有一个中间点(midIndex),value
            每次比较中间点和需要查找的那个值,然后每次移动中间点的位置
                midIndex<value
                    那么value必定在 [midIndex+1,endIndex]
                    startIndex=midIndex+1
                midIndex>value
                    那么value必定在 [startIndex,midIndex-1]
                    endIndex=midIndex-1

                midIndex=(startIndex + endIndex) /2

              边界情况:
                如果查找的值不在数组内,就会出现endIndex-1<0或者startIndex+1>arr.length,数组下标越界
              注意点:
                使用二分查找法,数组必须有序
         */
        int arr[] = {10,20,50,60,80,90};
        int value=50;
        System.out.println("extracted(arr) = " + extracted(arr,value));
    }

    private static int extracted(int [] arr,int value) {
        int startIndex = 0;
        int endIndex = arr.length - 1;

        while (startIndex<=endIndex) {
            int midIndex = (startIndex + endIndex) / 2;
            if (arr[midIndex] > value) {
                endIndex=midIndex-1;
            }else if(arr[midIndex] < value){
                startIndex = midIndex + 1;
            }else{
                return midIndex;
            }

        }
        return -1;
    }
}

package day11.codes;

class Gender{
  private String sex;  
  final static Gender MAN =new Gender("男");
    
  final static Gender WOMAN =new Gender("女");
    
  //通过私有的构造器禁止外部创建对象,只提供两个创建好的对象
    private Gender(){}
    private Gender(String sex){
        this.sex=sex;
    }
}
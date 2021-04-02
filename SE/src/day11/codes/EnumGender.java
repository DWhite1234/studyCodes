package day11.codes;

public enum EnumGender{
    MAN("男", "男人"),
    WOMAN("女", "女人");
    //    MAN {
//        @Override
//        public void run() {
//            System.out.println("达不溜");
//        }
//    },
//    WOMAN {
//        @Override
//        public void run() {
//            System.out.println("婀娜多姿");
//        }
//    };
    private String name;
    private String desc;

    EnumGender() {
    }

    EnumGender(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}

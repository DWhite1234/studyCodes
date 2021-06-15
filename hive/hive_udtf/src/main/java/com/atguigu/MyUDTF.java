package com.atguigu;


import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MyUDTF extends GenericUDTF {
    //需要手动重写该方法
    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
        //1.约束参数的个数
        if (argOIs.getAllStructFieldRefs().size() != 1) {
            throw new UDFArgumentLengthException("参数个数只能为1");
        }
        //2.约束参数的类型
        String typeName = argOIs.getAllStructFieldRefs().get(0).getFieldObjectInspector().getTypeName();
        if (!"string".equals(typeName)) {
            throw new UDFArgumentTypeException(0, "参数类型不正确");
        }
        //3.约束返回值类型
        List<String> listName = new ArrayList<>();
        listName.add("aaa");
        List<ObjectInspector> listArgs = new ArrayList<>();
        listArgs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        return ObjectInspectorFactory.getStandardStructObjectInspector(listName, listArgs);
    }

    //真正执行的逻辑,把一个json数组,炸成json对象
    @Override
    public void process(Object[] objects) throws HiveException {
        //获取传进来的字符串
        String jsonStr = objects[0].toString();
        //把一个json数组,炸成json对象
        JSONArray jsonArray = new JSONArray(jsonStr);
        for (int i = 0; i < jsonArray.length(); i++) {
            String string = jsonArray.getString(i);
            String[] strings = new String[]{string};
            //将炸开的一个个json对象写出
            forward(strings);
        }
    }

    @Override
    public void close() throws HiveException {

    }
}

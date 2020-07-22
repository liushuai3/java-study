package cn.lcools.jvm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by liushuai3 on 2019/7/24.
 */
public class MyClassLoader extends ClassLoader{

    //定义默认的class存放路径
    private final static Path DEFAUlT_CLASS_DIR = Paths.get("E:","classloader");
    private final Path classDir;

    public MyClassLoader(){
        super();
        this.classDir = DEFAUlT_CLASS_DIR;
    }
    public MyClassLoader(String classDir){
        super();
        this.classDir = Paths.get(classDir);
    }
    public MyClassLoader(String classDir,ClassLoader parent){
        super(parent);
        this.classDir = Paths.get(classDir);
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("自定义类加载器加载："+name);
        //读取class的二进制数据
        byte[] classBytes = readClassBytes(name);
        if(null == classBytes || classBytes.length == 0){
            throw new ClassNotFoundException("class "+ name + "未找到");
        }
        //调用defineClass方法定义class
        return this.defineClass(name,classBytes,0,classBytes.length);

    }
    private byte[] readClassBytes(String name) throws ClassNotFoundException{
        String classPath = name.replace(".","/");
        Path classFullPath = classDir.resolve(Paths.get(classPath+".class"));
        if(!classFullPath.toFile().exists()){
            throw new ClassNotFoundException("class "+ name + "未找到");
        }
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
            Files.copy(classFullPath,baos);
            return baos.toByteArray();
        }catch (IOException e){
            e.printStackTrace();
            throw new ClassNotFoundException("class "+ name + "未找到");
        }
    }

    @Override
    public String toString() {
        return "MyClassLoader";
    }
}

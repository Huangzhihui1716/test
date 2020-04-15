package webadv.s99201105.p02;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.apache.commons.codec.digest.DigestUtils;
public class App {
    public static void main(String[] args) {
    //先模拟账号密码
    saveUserInfo("17201313", sha256hex("123456"));
    //登录
    Scanner input = new Scanner(System.in);
    String userName="",password="";
    String[] userInfo = readUserInfo();
    while(true) {
	System.out.println("输入你的账号：");
	userName = input.nextLine();
	System.out.println("输入你的密码：");
	password =input.nextLine();
	if(userName.equals(userInfo[0]) &&sha256hex(password).equals(userInfo[1])) {
	    System.out.println("登录成功！");
	    break;
	}else {
	    System.out.println("账号或者密码错误！请重新输入！");
	 }
    }
    }
    public static String sha256hex(String input) {
        return DigestUtils.sha256Hex(input);
    }
    //SHA-25算法保护密码，将账号，密码保存在userInfo.txt中
    public static void saveUserInfo(String userName,String password) {
        BufferedWriter out;
	try {
	    out = new BufferedWriter(new FileWriter("userInfo.txt",true));
	    out.write(userName+"\n");
	    out.write(password);
	    out.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    //从文件中读取账号，密码
    public static String[] readUserInfo() {
    	String[] userInfo = new String[2];
    	try {
	        BufferedReader in = new BufferedReader(new FileReader("userInfo.txt"));
        	        userInfo[0] = in.readLine();
        	        userInfo[1] = in.readLine();
	        in.close();
	    } catch (IOException e) {
	    }
	    return userInfo;
    }
}
package vvr.web.utils;

import java.util.UUID;

/**
 * �ļ��ϴ��Ĺ�����
 * @author wwr
 *
 */
public class UploadUtils {

	public static String makeFileName(String fileName) {
		
		//�Ȳ�ѯ
		int index = fileName.lastIndexOf(".");
		
		//��ȡ
		String lastName = fileName.substring(index, fileName.length());
		
		//����Ψһ���ַ���,UUID���ɵ��ַ�����ʽqweqw-sad-asda  ���Խ�- �滻�ɿ��ַ���
		String name = UUID.randomUUID().toString().replaceAll("-", "");
		
		return name + lastName;
	}
	
	public static void main(String[] args) {
		String fileName = "qqq.jpg";
		String newFileName = makeFileName(fileName);
		System.out.println(newFileName);
	}
}

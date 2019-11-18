package _05_ReplaceSpaces;

public class Solution {
	public static String replaceSpaces(StringBuffer str) {
		String str1 = str.toString();
		if (str1.equals("")) return str1;
		char[] strArray = str1.toCharArray();

		int i = 0;
		int lengthSpaces = 0;
		while (i < strArray.length) {
			if (strArray[i] == ' ')
				lengthSpaces++;
			i++;
		}
		int newStrLength = strArray.length + lengthSpaces * 2;
		char[] newStr = new char[newStrLength];
		int indexOfOriginal = strArray.length - 1;
		int indexOfNew = newStrLength - 1;
		while (indexOfOriginal >= 0) {
			if(strArray[indexOfOriginal] != ' '){
				newStr[indexOfNew--] = strArray[indexOfOriginal--];
			}else{
				newStr[indexOfNew--] = '0';
				newStr[indexOfNew--] = '2';
				newStr[indexOfNew--] = '%';
				indexOfOriginal--;
			}
		}
		return new String(newStr);
	}

	public static void main(String[] args) {
		String res = replaceSpaces(new StringBuffer("hello world"));
		System.out.println(res);
	}
}

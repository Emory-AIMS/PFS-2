package common;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class StrUtil {
	/**
	 * append str2 in str1
	 * 
	 * @param str1
	 * @param str2
	 * @param sp
	 * @return
	 */
	public static String strAdd(String str1, String str2, String sp) {
		if (str1 == null)
			return str2;
		if (str2 == null)
			return str1;

		if(str1.trim().endsWith(";")){
			sp=" ";
		}
		return (str1.equals("")) ? str2 : str1 + sp + str2;
	}

	public static String strAdd(String str1, List<String> strList, int times,
			String sp) {
		if (strList == null || strList.size() == 0)
			return str1;
		if (str1 == null)
			str1 = "";
		for (int i = 1; i <= times; ++i) {
			for (int j = 0; j < strList.size(); ++j)
				str1 = strAdd(str1, strList.get(j), sp);
		}
		return str1;
	}

	/**
	 * get the pieces that str can be divided by delimiter
	 * 
	 * @param str
	 * @param delimiter
	 * @return
	 */
	public static int strLen(String str, String delimiter) {
		if (str == null || str.length() == 0)
			return 0;
		if (delimiter == null || delimiter.length() == 0)
			return 1;
		else {
		//	Matcher m = Pattern.compile(delimiter).matcher(str);
			Matcher m = Pattern.compile(delimiter).matcher(str.trim());
			int len = 0;
			while (m.find())
				len++;
			return len + 1;
		}
	}

	/**
	 * get the pieces that str can be divided by delimiter
	 * 
	 * @param str
	 * @param delimiter
	 * @return
	 */

	public static int strLen1(String str, String delimiter) {
		if (str == null || str.length() == 0)
			return 0;
		if (delimiter == null || delimiter.length() == 0)
			return 1;
		else {
			List<String> itemsets = new ArrayList<String>(Arrays.asList(str.trim().split(delimiter))); // split("\\s+|;")));
			itemsets.removeAll(Arrays.asList(null,""));    // remove any null item
		 	int count=0;
	//	 	itemsets.removeAll(Arrays.asList(null,"")); 
		 	for(String item: itemsets){
				List<String> itemset = new ArrayList<String>(Arrays.asList(item.trim().split("\\s+")));   // list of all items in the first  itemset
				itemset.removeAll(Arrays.asList(null,""));    // remove any null item
		 		count+=itemset.size();
			}
			return count;
		}
	}

	/**
	 * get the pieces that str can be divided by delimiter
	 * 
	 * @param str
	 * @param delimiter
	 * @return
	 */

	public static int strLen2(String str) {
		if (str == null || str.length() == 0)
			return 0;
			else {
			List<String> itemsets = new ArrayList<String>(Arrays.asList(str.trim().split("\\s+|;")));
			itemsets.removeAll(Arrays.asList(null,""));    // remove any null item
		 	int count=0;
	//	 	itemsets.removeAll(Arrays.asList(null,"")); 
			return itemsets.size();
		}
	}
	
	/**
	 * judge whether str1 contains str2, both strings are seperated by sp
	 * 
	 * @param str1
	 * @param str2
	 * @param sp
	 * @return
	 */
	public static boolean strContain(String str1, String str2, String sp) {
		String[] items = str2.split(sp);
		str1 = sp + str1 + sp;
		int index = 0;
		for (String item : items) {
			index = str1.indexOf(sp + item + sp, index);
			if (index == -1)
				return false;
			index += item.length();
		}
		return true;
	}

	public static boolean strContainN1(String str1, String str2, String sp) {
		List<String> itemsets = new LinkedList<String>(Arrays.asList(str1.split(";")));
		itemsets.removeAll(Arrays.asList("", null));

		List<String> items = new LinkedList<String>(Arrays.asList(str2.split(";")));
		items.removeAll(Arrays.asList("", null));

		if(PatternDeal2.isPatternContainedAll(str1, str2, ";"))
				return true;
		else
			return false;
	}
	
	public static boolean strContain(String str1, String[] str2, String sp,
			int gap, int preIndex, int pointer) {
		if (str2.length == 0 || pointer == str2.length)
			return true;
		if (str1.trim().length() == 0)
			return false;
		int index = 0;
		if (preIndex == -2)
			index = (sp + str1 + sp).indexOf(sp + str2[pointer] + sp);
		else
			index = (sp + str1 + sp).indexOf(sp + str2[pointer] + sp,
					preIndex + 1);
		while (index != -1
				&& (preIndex == -2 || index - preIndex <= gap + 2 * sp.length()
						+ 1)) {
			if (strContain(str1, str2, sp, gap, index, pointer + 1))
				return true;
			else {
				index = (sp + str1 + sp).indexOf(sp + str2[pointer] + sp,
						index + 1);
			}
		}
		return false;
	}

	
	public static int strContain( String[] aStr1, String[] aStr2, int nStr1Start, int nStr2Pointer, int nGap ){
		
		int nMaxLast = -1;
		int nEnd = Math.min( nStr1Start + nGap + 1, aStr1.length );
		
		if( nStr2Pointer == aStr2.length ){
			return nStr1Start - 1;
		}
		
		for( int i = nEnd;i > nStr1Start;i-- ){
			if( aStr1[i - 1].equals(aStr2[nStr2Pointer]) ){
				int nLast = strContain( aStr1, aStr2, i, nStr2Pointer + 1, nGap );
				if( nLast > nMaxLast ){
					nMaxLast = nLast;
				}
			}
		}
		
		return nMaxLast;
	}
	
	
	
	
	public static void main(String[] args) {

		Random rand = new Random(); 
		
		System.out.println("  "+strLen1("a ; b c ; d 3 f gh ; 1 ; a b c",";"));
		System.out.println("  "+strLen2("a ; b ; c ; d 3 f    gh ;     ;  ;  1 ; a b c"));
		
 		
	}
	
}

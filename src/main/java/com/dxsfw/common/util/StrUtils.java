package com.dxsfw.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 字符串操作工具
 * 
 * @Author leo.zhou
 * @CreateDate 2013-7-24
 * @Version 1.0   
 */
public class StrUtils {
	/**
	 * 是否有中文字符
	 * 
	 * @param s
	 * @return
	 */
	public static boolean hasCn(String s) {
		if (s == null) {
			return false;
		}
		return countCn(s) > s.length();
	}

	/**
	 * 获得字符。符合中文习惯。
	 * 
	 * @param s
	 * @param length
	 * @return
	 */
	public static String getCn(String s, int len, String replace) {
		
		if(replace == null)
			replace = "...";
		
		if (s == null) {
			return s;
		}
		int sl = s.length();
		int maxCount = len;
		int count = 0;
		int i = 0;
		while (count < maxCount && i < sl) {
			if (s.codePointAt(i) < 256) {
				count++;
			} else {
				count += 2;
			}
			i++;
		}
		if (count > maxCount) {
			i--;
		}
		
		if(i == sl)
			return s.substring(0, i);
		else
			return s.substring(0, i) + replace;
	}

	/**
	 * 计算GBK编码的字符串的字节数
	 * 
	 * @param s
	 * @return
	 */
	public static int countCn(String s) {
		if (s == null) {
			return 0;
		}
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.codePointAt(i) < 256) {
				count++;
			} else {
				count += 2;
			}
		}
		return count;
	}

	/**
	 * 文本转html
	 * 
	 * @param txt
	 * @return
	 */
	public static String txt2htm(String txt) {
		if (StringUtils.isBlank(txt)) {
			return txt;
		}
		StringBuilder sb = new StringBuilder((int) (txt.length() * 1.2));
		char c;
		for (int i = 0; i < txt.length(); i++) {
			c = txt.charAt(i);
			switch (c) {
			case '&':
				sb.append("&amp;");
				break;
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;
			case '"':
				sb.append("&quot;");
				break;
			case ' ':
				sb.append("&nbsp;");
				break;
			case '\n':
				sb.append("<br/>");
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}

	/**
	 * html转文本
	 * 
	 * @param htm
	 * @return
	 */
	public static String htm2txt(String htm) {
		if (htm == null) {
			return htm;
		}
		htm = htm.replace("&amp;", "&");
		htm = htm.replace("&lt;", "<");
		htm = htm.replace("&gt;", ">");
		htm = htm.replace("&quot;", "\"");
		htm = htm.replace("&nbsp;", " ");
		htm = htm.replace("<br/>", "\n");
		return htm;
	}

	/**
	 * 替换字符串
	 * 
	 * @param sb
	 * @param what
	 * @param with
	 * @return
	 */
	public static StringBuilder replace(StringBuilder sb, String what,
			String with) {
		int pos = sb.indexOf(what);
		while (pos > -1) {
			sb.replace(pos, pos + what.length(), with);
			pos = sb.indexOf(what);
		}
		return sb;
	}

	/**
	 * 替换字符串
	 * 
	 * @param s
	 * @param what
	 * @param with
	 * @return
	 */
	public static String replace(String s, String what, String with) {
		return replace(new StringBuilder(s), what, with).toString();
	}

	/**
	 * 全角-->半角
	 * 
	 * @param qjStr
	 * @return
	 */
	public String Q2B(String qjStr) {
		String outStr = "";
		String Tstr = "";
		byte[] b = null;
		for (int i = 0; i < qjStr.length(); i++) {
			try {
				Tstr = qjStr.substring(i, i + 1);
				b = Tstr.getBytes("unicode");
			} catch (java.io.UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if (b[3] == -1) {
				b[2] = (byte) (b[2] + 32);
				b[3] = 0;
				try {
					outStr = outStr + new String(b, "unicode");
				} catch (java.io.UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else
				outStr = outStr + Tstr;
		}
		return outStr;
	}

	public static final char[] N62_CHARS = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z' };
	public static final char[] N36_CHARS = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z' };

	private static StringBuilder longToNBuf(long l, char[] chars) {
		int upgrade = chars.length;
		StringBuilder result = new StringBuilder();
		int last;
		while (l > 0) {
			last = (int) (l % upgrade);
			result.append(chars[last]);
			l /= upgrade;
		}
		return result;
	}

	/**
	 * 长整数转换成N62
	 * 
	 * @param l
	 * @return
	 */
	public static String longToN62(long l) {
		return longToNBuf(l, N62_CHARS).reverse().toString();
	}

	public static String longToN36(long l) {
		return longToNBuf(l, N36_CHARS).reverse().toString();
	}

	/**
	 * 长整数转换成N62
	 * 
	 * @param l
	 * @param length
	 *            如N62不足length长度，则补足0。
	 * @return
	 */
	public static String longToN62(long l, int length) {
		StringBuilder sb = longToNBuf(l, N62_CHARS);
		for (int i = sb.length(); i < length; i++) {
			sb.append('0');
		}
		return sb.reverse().toString();
	}

	public static String longToN36(long l, int length) {
		StringBuilder sb = longToNBuf(l, N36_CHARS);
		for (int i = sb.length(); i < length; i++) {
			sb.append('0');
		}
		return sb.reverse().toString();
	}

	/**
	 * N62转换成整数
	 * 
	 * @param n62
	 * @return
	 */
	public static long n62ToLong(String n62) {
		return nToLong(n62, N62_CHARS);
	}

	public static long n36ToLong(String n36) {
		return nToLong(n36, N36_CHARS);
	}

	private static long nToLong(String s, char[] chars) {
		char[] nc = s.toCharArray();
		long result = 0;
		long pow = 1;
		for (int i = nc.length - 1; i >= 0; i--, pow *= chars.length) {
			int n = findNIndex(nc[i], chars);
			result += n * pow;
		}
		return result;
	}

	private static int findNIndex(char c, char[] chars) {
		for (int i = 0; i < chars.length; i++) {
			if (c == chars[i]) {
				return i;
			}
		}
		throw new RuntimeException("N62(N36)非法字符：" + c);
	}
	
	/**
	 * 判断字符串是否为 NULL或""
	 * @param str
	 * @return
	 */
	public static boolean isNil(String str) {
		if (str == null || str.length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串不是否为 NULL或""
	 * @param str
	 * @return
	 */
	public static boolean isNotNil(String str) {
		return !isNil(str);
	}
	
	//取文件字符串后缀名
	public static String getFileSuffix(String fileName) {

	      int lastIndexOfDot = fileName.lastIndexOf('.');
	      int fileNameLength = fileName.length();
	      final String extension = fileName.substring(lastIndexOfDot+1, fileNameLength);
	      return "."+extension;   
	}
	
	/**
     * 得到纯文本，过滤了各种标记，也过滤掉了js,style中间的文本
     * @param text
     * @return
     */
    public static String getText(String text) {
        StringBuilder builder = new StringBuilder("");
        Boolean accept = Boolean.TRUE; //允许接收字符
        Boolean acceptSign = Boolean.TRUE; //允许接收标记
        Boolean acceptPlainText = Boolean.TRUE;//是否允许接收标记中的纯文本
        int maxIdx = text.length() - 1;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            switch (c) {
                case '<':
                    if (++i >= maxIdx) break;
                    char t = text.charAt(i);
                    if (t == 's' || t == 'S') {//处理<style> <script>标记
                        char x = text.charAt(++i);
                        if (x == 't' || x == 'T') { //处理<style>
                            char y = text.charAt(++i);
                            if (y == 'y' || y == 'Y') {
                                acceptPlainText = Boolean.FALSE;
                                accept = Boolean.FALSE;
                                acceptSign = Boolean.FALSE;
                                continue;
                            }
                        } else if (x == 'c' || x == 'C') { //处理<script>标记
                            char y = text.charAt(++i);
                            if (y == 'r' || x == 'R') {
                                acceptPlainText = Boolean.FALSE;
                                accept = Boolean.FALSE;
                                acceptSign = Boolean.FALSE;
                                continue;
                            }
                        }
                    } else if (t == '!') {
                        char x = text.charAt(++i);
                        if (x == '-') {
                            acceptPlainText = Boolean.FALSE;
                            accept = Boolean.FALSE;
                            acceptSign = Boolean.FALSE;
                            continue;
                        } else {
                            acceptPlainText = Boolean.TRUE;
                        }
                    } else if (t == '/') {//相应的关闭标记
                        acceptPlainText = Boolean.TRUE;
                    }
                    accept = Boolean.FALSE;
                    acceptSign = Boolean.FALSE;
                    break;
                case '>':
                    if (acceptPlainText) {
                        accept = Boolean.TRUE;
                        if (acceptSign) builder.append('>');
                        acceptSign = Boolean.TRUE;
                    }
                    break;
                case '\t':
                    break;
                case '\n':
                    break;
                default:
                    if (accept && acceptPlainText) {
                        builder.append(c);
                    }
            }
        }
        return builder.toString().trim();
    }
    
    /**
     * 正则表达式过滤html标签
     * @param htmlStr
     * @return
     */
    public static String delHTMLTag(String htmlStr) {
        String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式 
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签 
        
        String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符 
        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签 
        
        htmlStr = htmlStr.replaceAll("&nbsp;", "");
        return htmlStr.trim(); // 返回文本字符串 
    }
}


/**
 * Created by eclipse3.2.
 * function:
 * User: FMD(冯敏栋)
 * Date: 2011-1-10
 * Time: 下午12:10:11
 * Email:fmdsaco99@163.com
 * To change this template use File | Settings | File Templates.
 */
package onther;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

 

public class Extract {
	Log log = LogFactory.getLog(this.getClass().getName());
	
	
	public static void main(String[] arg) {
		
//		SocialPaymentDetail model = new SocialPaymentDetail();
//		model.setInsuranceBreeding2(8238.09f);
//		Extract e=new Extract();
//	String str=	e.getValue(model, "insuranceBreeding2");
//		 System.out.println(str);
	}
	
	public  synchronized String getValue(Object resource, String setMethodName) {
		setMethodName = changeMethodName(setMethodName);
		String oValue ="";
		try {
			if (null == resource || null == setMethodName) {
				log.debug("setResValue() in extractDataOfPage value is null:");
				return "";
			}


			Method getMeth = resource.getClass().getMethod("get" + setMethodName.trim());
		
			Object obj = getMeth.invoke(resource);
			oValue=obj.toString();
//			System.out.println(oValue);
//			Object arglist[] = new Object[1];
//			arglist[0] = value;
//			setMeth.invoke(resource, arglist);
		} catch (NoSuchMethodException e) {
			// 不抛出
			log.error("NoSuchMethodException: ", e);
		} catch (Exception e) {
			log.error("NoSuchMethodException: ", e);
		}

		
		return oValue;
	}
	
	public synchronized void setValue(Object resource, String setMethodName,Object value) {
		setMethodName = changeMethodName(setMethodName);
		try {
			if (null == resource || null == setMethodName || null == value) {
				log.debug("setResValue() in extractDataOfPage value is null:");
				return;
			}
			Class partypes[] = new Class[1];
			String className = value.getClass().getName();
			if ("java.lang.String".equals(className)) {
				partypes[0] = String.class;
			} else if ("java.lang.Long".equals(className)) {
				partypes[0] = Long.class;
			}
			Method getMeth = resource.getClass().getMethod("get" + setMethodName.trim());
			String oValue = (String) getMeth.invoke(resource);
			if (null != oValue) {
				value = oValue + value;
			}

			Method setMeth = resource.getClass().getMethod("set" + setMethodName.trim(), partypes);
			Object arglist[] = new Object[1];
			arglist[0] = value;
			setMeth.invoke(resource, arglist);
		} catch (NoSuchMethodException e) {
			// 不抛出
			log.error("NoSuchMethodException: ", e);
		} catch (Exception e) {
			log.error("NoSuchMethodException: ", e);
		}

	}
	
	
	/**
	 * 
	 * @param key
	 * @param content
	 * @return
	 */
    public static String checks(String key,String content){
        if(key==null)return "";
        if(key.trim().equals(""))return "";
        Pattern p = Pattern.compile(key.trim());

        Matcher m = p.matcher(content);
    StringBuffer sb = new StringBuffer();
       
 
        boolean result = m.find();
 
        if(result) {
        	
        	return m.group(1).trim();
        }
      

      

        
        return sb.toString();
        
    }
	private String changeMethodName(String methodName) {
		if (methodName.length() > 1) {
			methodName = (methodName.substring(0, 1)).toUpperCase()
					+ methodName.substring(1, methodName.length());
		}
		return methodName;
	}
}

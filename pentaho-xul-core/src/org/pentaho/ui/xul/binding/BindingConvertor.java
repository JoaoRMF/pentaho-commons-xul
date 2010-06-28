package org.pentaho.ui.xul.binding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public abstract class BindingConvertor<V, R> {
  private static final long serialVersionUID = 1L;

  public enum Direction{FORWARD, BACK};
  
  public abstract R sourceToTarget(V value);

  public abstract V targetToSource(R value);

  private static BindingConvertor<Integer, String> integer2String = new Integer2String();
  private static BindingConvertor<Long, String> long2String = new Long2String();
  private static BindingConvertor<String, String> string2String = new String2String();
  private static BindingConvertor<Boolean, String> boolean2String = new Boolean2String();
  
  public static BindingConvertor<Integer, String> integer2String() {
    return integer2String;
  }
  
  public static BindingConvertor<Long, String> long2String() {
    return long2String;
  }
  
  public static BindingConvertor<Date, String> date2String() {
    return date2String(new SimpleDateFormat("MM-dd-yyyy")); //$NON-NLS-1$
  }
  
  public static BindingConvertor<Date, String> date2String(final DateFormat format) {
    return new Date2String(format);
  }
  
  public static BindingConvertor<String, String> string2String() {
    return string2String;
  }
  
  public static BindingConvertor<Boolean, String> boolean2String() {
    return boolean2String ;
  }
  
  /*
   * Canned BindingConverter Implementations here
   */
  static class Integer2String extends BindingConvertor<Integer, String> {
    public String sourceToTarget(Integer value) {
      if (value != null) {
        return value.toString();
      } else {
        return ""; //$NON-NLS-1$
      }
    }
  
    public Integer targetToSource(String value) {
      if (value != null) {
        try {
          return Integer.valueOf(value);
        } catch (NumberFormatException e) {            
          return new Integer(0);
        }
      }
      return new Integer(0);
    }
  }

  static class Long2String extends BindingConvertor<Long, String> {
    public String sourceToTarget(Long value) {
      if (value != null) {
        return value.toString();
      } else {
        return ""; //$NON-NLS-1$
      }
    }
  
    public Long targetToSource(String value) {
      if (value != null) {
        try {            
          return Long.valueOf(value); 
        } catch (NumberFormatException e) {
          return new Long(0);
        }
      }
      return new Long(0);
    }
  }

  static class Date2String extends BindingConvertor<Date, String> {
    DateFormat format = null;
    
    public Date2String() {
      format = new SimpleDateFormat("MM-dd-yyyy"); //$NON-NLS-1$
    }
    public Date2String(DateFormat format) {
      this.format = format;
    }
    
    public String sourceToTarget(Date value) {
      if (value == null) {
        return ""; //$NON-NLS-1$
      }
      return format.format(value);
    }
  
    public Date targetToSource(String value) {
      try {
        return format.parse(value);
      } catch (Exception e) {
        return null;
      }
    }
  }

  static class String2String extends BindingConvertor<String, String> {
    public String sourceToTarget(String value) {
      return value;
    }

    public String targetToSource(String value) {
      return value; 
    }
  }
                                                                       
  static class Boolean2String extends BindingConvertor<Boolean, String> {
    public String sourceToTarget(Boolean value) {
      return value.toString();
    }

    public Boolean targetToSource(String value) {
      return Boolean.parseBoolean(value);
    }
    
  }
  
  
}

package org.pentaho.ui.xul.binding;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;


public class BindingConvertorTest {

  @Test
  public void testInteger2String_source() throws Exception {
    BindingConvertor<Integer, String> bc = BindingConvertor.integer2String();
    
    String converted = bc.sourceToTarget(new Integer(10));
    assertEquals("10", converted);
    
    converted = bc.sourceToTarget(new Integer(-10));
    assertEquals("-10", converted);
    
    converted = bc.sourceToTarget(null);
    assertEquals("", converted);
    
    converted = bc.sourceToTarget(Integer.MAX_VALUE);    
    assertEquals("" + Integer.MAX_VALUE, converted);

    converted = bc.sourceToTarget(Integer.MIN_VALUE);
    assertEquals("" + Integer.MIN_VALUE, converted);
    
  }
  
  @Test
  public void testInteger2String_target() throws Exception {
    BindingConvertor<Integer, String> bc = BindingConvertor.integer2String();
    Integer converted = bc.targetToSource("10");
    assertEquals(Integer.valueOf(10), converted);
    
    converted = bc.targetToSource("-10");
    assertEquals(Integer.valueOf(-10), converted);

    converted = bc.targetToSource("");
    assertEquals(Integer.valueOf(0), converted);

    converted = bc.targetToSource("" + Integer.MAX_VALUE);
    assertEquals(Integer.valueOf(Integer.MAX_VALUE), converted);

    converted = bc.targetToSource("" + Integer.MIN_VALUE);
    assertEquals(Integer.valueOf(Integer.MIN_VALUE), converted);
  
  }
  
  @Test
  public void testLong2String_source() throws Exception {
    BindingConvertor<Long, String> bc = BindingConvertor.long2String();
    
    String converted = bc.sourceToTarget(new Long(10));
    assertEquals("10", converted);
    
    converted = bc.sourceToTarget(new Long(-10));
    assertEquals("-10", converted);
    
    converted = bc.sourceToTarget(null);
    assertEquals("", converted);
    
    converted = bc.sourceToTarget(Long.MAX_VALUE);    
    assertEquals("" + Long.MAX_VALUE, converted);

    converted = bc.sourceToTarget(Long.MIN_VALUE);
    assertEquals("" + Long.MIN_VALUE, converted);
    
  }
  
  @Test
  public void testLong2String_target() throws Exception {
    BindingConvertor<Long, String> bc = BindingConvertor.long2String();
    Long converted = bc.targetToSource("10");
    assertEquals(Long.valueOf(10), converted);
    
    converted = bc.targetToSource("-10");
    assertEquals(Long.valueOf(-10), converted);

    converted = bc.targetToSource("");
    assertEquals(Long.valueOf(0), converted);

    converted = bc.targetToSource("" + Long.MAX_VALUE);
    assertEquals(Long.valueOf(Long.MAX_VALUE), converted);

    converted = bc.targetToSource("" + Long.MIN_VALUE);
    assertEquals(Long.valueOf(Long.MIN_VALUE), converted);
  
  }
  
  @Test
  public void testDate2String_source() throws Exception {
    BindingConvertor<Date, String> bc = BindingConvertor.date2String();
    Date date = new Date();
    DateFormat format = new SimpleDateFormat("MM-dd-yyyy");
    String converted = bc.sourceToTarget(date);
    assertEquals(format.format(date), converted);
    
    converted = bc.sourceToTarget(null);
    assertEquals("", converted);
    
    format = new SimpleDateFormat("yyyy-MM-dd");
    bc = BindingConvertor.date2String(format);
    converted = bc.sourceToTarget(date);
    assertEquals(format.format(date), converted);
    
  }
  
  @Test
  public void testDate2String_target() throws Exception {
    BindingConvertor<Date, String> bc = BindingConvertor.date2String();
    Date date = new Date();
    DateFormat format = new SimpleDateFormat("MM-dd-yyyy");
    Date converted = bc.targetToSource(format.format(date));
    assertEquals(format.parse(format.format(date)), converted);
    
    converted = bc.targetToSource("234234234234324");
    assertEquals(null, converted);
    
  }
  
  @Test
  public void testString2String() throws Exception {
    BindingConvertor<String, String> bc = BindingConvertor.string2String();
    String source= "Hello Test!";
    String converted = bc.sourceToTarget(source);
    assertEquals(source, converted);

    
    String target = "Howdy!";
    converted = bc.targetToSource(target);
    assertEquals(target, converted);
  }
  
  @Test
  public void testBoolean2String() throws Exception {
    BindingConvertor<Boolean, String> bc = BindingConvertor.boolean2String();
    assertEquals("true", bc.sourceToTarget(true));
    assertEquals("false", bc.sourceToTarget(false));
    
    assertTrue(bc.targetToSource("true"));
    assertFalse(bc.targetToSource("false"));
    assertFalse(bc.targetToSource(null));
    assertFalse(bc.targetToSource("anything"));
  }
  
}

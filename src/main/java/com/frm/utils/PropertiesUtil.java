package com.frm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

public abstract class PropertiesUtil
{
  public static Hashtable<String, String> getPropertiesAsHashtable(String propertiesFilePath) 
  {
    Hashtable<String, String> propertiesTable = null;

    if (!new File(propertiesFilePath).exists()) 
    {
      return propertiesTable;
    }

    try
    {
      Properties properties = new Properties();
      InputStream inputStream = new FileInputStream(propertiesFilePath);

      properties.load(inputStream);

      propertiesTable = new Hashtable<>();
      
      for (final String key : properties.stringPropertyNames()) 
      {
        String value = properties.getProperty(key);
        propertiesTable.put(key, value != null ? value.trim() : value);
      }

    } catch (Exception e) 
    {
    	e.printStackTrace();
    }

    return propertiesTable;
  }

  public static synchronized String getProperty(String propertiesFilePath, String key) 
  {
    String value = null;

    if (key == null || key.trim().length() == 0) 
    {
      return value;
    }

    if (!new File(propertiesFilePath).exists()) 
    {
      return value;
    }

    try
    {
      Properties properties = new Properties();
      InputStream inputStream = new FileInputStream(propertiesFilePath);

      properties.load(inputStream);

      value = properties.getProperty(key);
      value = value != null ? value.trim() : value;

    } catch (Exception e) 
    {
    	e.printStackTrace();
    }

    return value;
  }
}

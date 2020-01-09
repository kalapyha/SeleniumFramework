package config;

import com.sun.scenario.effect.impl.prism.PrReflectionPeer;

import java.io.*;
import java.util.Properties;

public class PropertiesFile {
    static Properties prop = new Properties();
    static String projectPath = System.getProperty("user.dir");
    public static void main(String[] args) {
        getProperties();
        setProperties();
        getProperties();

    }

    public static void getProperties() {


        try{
            InputStream input = new FileInputStream(projectPath + "/src/test/java/config/config.properties");
            prop.load(input);
            String browser = prop.getProperty("browser");
            System.out.println(browser);
        }
        catch(FileNotFoundException e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static void setProperties() {


        try{
            OutputStream output = new FileOutputStream(projectPath + "/src/test/java/config/config.properties");
            prop.setProperty("browser", "safari");
            prop.store(output, null);
        }
        catch(FileNotFoundException e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    }

/*
 * MetnalHealthLiberiaApp.java
 */

package mentalhealthliberia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class MentalHealthLiberiaApp extends SingleFrameApplication {

    private final static int versionNum = 1;
    
    private final static String uploadUrl = "http://50.57.158.90:8080/MHL/patientEncounterForm/upload";
    private static String dataDirectory = "";
    
    private static boolean processConfig(String configPath) {
        try {
            File file = new File(configPath);
            InputStream stream = new FileInputStream(file);
            Properties config = new Properties();
            config.load(stream);
            
            // load the configuration properties
            dataDirectory = config.getProperty("data_dir");
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public int getVersionNum() {
        return versionNum;
    }
    
    public String getDataDirectory() {
        return dataDirectory;
    }
    
    public String getUploadUrl() {
        return uploadUrl;
    }
    
    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new MentalHealthLiberiaView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of MetnalHealthLiberiaApp
     */
    public static MentalHealthLiberiaApp getApplication() {
        return Application.getInstance(MentalHealthLiberiaApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        if (processConfig(args[0])) {
            launch(MentalHealthLiberiaApp.class, args);
        }
    }
}

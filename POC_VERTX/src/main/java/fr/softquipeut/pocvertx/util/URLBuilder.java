package fr.softquipeut.pocvertx.util;

/**
 *
 * @author Joce
 */
public class URLBuilder {
    
    private static final int SERVER_PORT = 80;
    private static final String SERVER_ADDR = "82.233.147.73";
    private static final String SERVER_PREFIX = "http://" + SERVER_ADDR+":"+SERVER_PORT+"/";
    
    private String serverRoot;
    
    public static final URLBuilder DEFAULT = new URLBuilder(SERVER_PREFIX);

    private URLBuilder(String serverRoot) {
        this.serverRoot = serverRoot;
    }
    
    public String getAddress(String moduleSuffix){
        return serverRoot + moduleSuffix;        
    }
    
    
}

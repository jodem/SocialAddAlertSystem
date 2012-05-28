package fr.softquipeut.pocvertx.representation;

import com.google.common.base.Objects;


/**
 * Mother resource class
 * @author Joce
 */
public class BasicRepresentation {
    
    private final String $key;
    private final String $title;    
    
    public BasicRepresentation(String key, String title) {
        this.$key = key;
        this.$title = title;        
    }

    public String getKey() {
        return $key;
    }

    public String getTitle() {
        return $title;
    }

   
    
    

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("$key", $key).add("$title", $title).toString();
    }
    
    
    
        
    
}

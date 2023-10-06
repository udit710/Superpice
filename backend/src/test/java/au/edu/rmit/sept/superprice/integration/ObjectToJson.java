package au.edu.rmit.sept.superprice.integration;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJson {
    
    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception err) {
            throw new RuntimeException(err);
        }
    }
    
}

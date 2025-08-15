
package br.com.banco.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class JsonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void salvar(Object obj, File file) {
        try {
            file.getParentFile().mkdirs();
            MAPPER.writerWithDefaultPrettyPrinter().writeValue(file, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> T carregar(File file, Class<T> clazz) {
        try {
            if (!file.exists()) return null;
            return MAPPER.readValue(file, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

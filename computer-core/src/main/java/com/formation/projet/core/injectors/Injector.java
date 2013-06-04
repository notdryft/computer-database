package com.formation.projet.core.injectors;

import com.formation.projet.core.annotations.Property;
import com.formation.projet.core.annotations.PropertyClass;
import com.formation.projet.core.exceptions.PropertyInjectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 30/05/13
 * Time: 17:43
 */
public class Injector {

    private static final Logger logger = LoggerFactory.getLogger(Injector.class);

    private static <T> T loadProperties0(Class<T> clazz) throws Exception {
        Field[] allFields = clazz.getDeclaredFields();

        List<Field> fields = new ArrayList<Field>();
        for (Field field : allFields) {
            if (!Modifier.isStatic(field.getModifiers())) {
                fields.add(field);
            }
        }

        Constructor<T> constructor;
        constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);

        T t = constructor.newInstance();

        String path = clazz.getAnnotation(PropertyClass.class).value();
        Properties properties = Properties.loadProperties(path);

        for (Field field : fields) {
            field.setAccessible(true);

            Property property = field.getAnnotation(Property.class);
            String name = property.value();

            properties.tryProperty(name);
            properties.cleanTaggedProperty(name);

            Class<?> fieldClass = field.getType();

            Method method = properties.getMethod(fieldClass);
            Object object = method.invoke(properties, name);
            field.set(t, object);
        }

        return t;
    }

    public static <T> T loadProperties(Class<T> clazz) {
        logger.debug("Injecting properties for bean {}", clazz);

        try {
            return loadProperties0(clazz);
        } catch (Exception e) {
            logger.error("Cannot load property {}", clazz);

            throw new PropertyInjectionException(String.format("Cannot load property %s", clazz), e);
        }
    }
}

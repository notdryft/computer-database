package com.formation.projet.injectors;

import com.formation.projet.annotations.Property;
import com.formation.projet.annotations.PropertyClass;
import com.formation.projet.exceptions.PropertyInjectionException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
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

            // TODO Dirty
            Class<?> fieldClass = field.getType();
            if (fieldClass.isAssignableFrom(int.class)) {
                field.set(t, properties.getInt(name));
            } else if (fieldClass.isAssignableFrom(String.class)) {
                field.set(t, properties.getString(name));
            } else if (fieldClass.isAssignableFrom(List.class)) {
                field.set(t, properties.getList(name));
            } else {
                throw new PropertyInjectionException("Not managed field type");
            }
        }

        return t;
    }

    public static <T> T loadProperties(Class<T> clazz) {
        try {
            return loadProperties0(clazz);
        } catch (Exception e) {
            throw new PropertyInjectionException(String.format("Cannot load property class \"%s\"", clazz.getSimpleName()), e);
        }
    }
}

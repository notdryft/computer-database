package com.formation.projet.properties;

import com.formation.projet.annotations.Property;
import com.formation.projet.annotations.PropertyClass;
import com.formation.projet.annotations.PropertyType;
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
public class Loader<T> {

    public Loader() {
        // Do nothing
    }

    private T loadProperties0(Class<T> clazz) throws Exception {
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
            String property = field.getAnnotation(Property.class).value();

            properties.tryProperty(property);
            properties.cleanTaggedProperty(property);

            if (field.getAnnotation(PropertyType.class) == null) {
                field.set(t, properties.getString(field.getAnnotation(Property.class).value()));
            } else {
                field.set(t, properties.getInt(field.getAnnotation(Property.class).value()));
            }
        }

        return t;
    }

    public T loadProperties(Class<T> clazz) {
        try {
            return loadProperties0(clazz);
        } catch (Exception e) {
            throw new PropertyInjectionException(String.format("Cannot load property class \"%s\"", clazz.getSimpleName()), e);
        }
    }
}

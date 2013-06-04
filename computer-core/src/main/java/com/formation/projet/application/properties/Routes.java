package com.formation.projet.application.properties;

import com.formation.projet.application.annotations.Property;
import com.formation.projet.application.annotations.PropertyClass;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 31/05/13
 * Time: 10:15
 */
@PropertyClass("routes.properties")
public class Routes {

    private static Routes instance;

    @Property("routes.index")
    private String index;

    @Property("routes.edit")
    private String edit;

    @Property("routes.create")
    private String create;

    @Property("routes.back")
    private String back;

    private Routes() {
        // Do nothing.
    }

    public String getIndex() {
        return index;
    }

    public String getEdit() {
        return edit;
    }

    public String getCreate() {
        return create;
    }

    public String getBack() {
        return back;
    }

    public static Routes getInstance() {
        return instance;
    }
}

package com.formation.projet.properties;

import com.formation.projet.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: gcorre
 * Date: 31/05/13
 * Time: 10:15
 */
public enum Routes {
    instance;

    private static final String PATH = "routes.properties";

    private static final String INDEX = "routes.index";

    private static final String EDIT = "routes.edit";

    private static final String CREATE = "routes.create";

    private static final String BACK = "routes.back";

    private String index;

    private String edit;

    private String create;

    private String back;

    private Routes() {
        Properties properties =
                Properties.loadProperties(
                        PATH,
                        INDEX, EDIT, CREATE, BACK);

        this.index = properties.getString(INDEX);
        this.edit = properties.getString(EDIT);
        this.create = properties.getString(CREATE);
        this.back = properties.getString(BACK);
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
}

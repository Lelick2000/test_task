package project.factories;

import framework.base.BaseForm;

public class PageFactory extends ObjectFactory<BaseForm> {
    private static volatile PageFactory pageFactory = null;

    private PageFactory() {
    }

    public static PageFactory getFactory() {
        PageFactory localInstance = pageFactory;
        if (localInstance == null) {
            synchronized (PageFactory.class) {
                localInstance = pageFactory;
                if (localInstance == null) {
                    pageFactory = localInstance = new PageFactory();
                }
            }
        }
        return localInstance;
    }

}

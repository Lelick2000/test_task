package project.factories;

import project.actions.steps.BasePageSteps;

public class StepsFactory extends ObjectFactory<BasePageSteps> {

    private static volatile StepsFactory stepsFactory = null;

    private StepsFactory() {
    }

    public static StepsFactory getFactory() {
        StepsFactory localInstance = stepsFactory;
        if (localInstance == null) {
            synchronized (StepsFactory.class) {
                localInstance = stepsFactory;
                if (localInstance == null) {
                    stepsFactory = localInstance = new StepsFactory();
                }
            }
        }
        return localInstance;
    }
}

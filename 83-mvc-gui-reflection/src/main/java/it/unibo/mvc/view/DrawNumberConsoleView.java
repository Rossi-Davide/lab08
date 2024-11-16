package it.unibo.mvc.view;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

public class DrawNumberConsoleView implements DrawNumberView{

    private static final String VIEW_NAME = "Output number console view";

    @Override
    public void setController(final DrawNumberController observer) {
        return;
    }

    @Override
    public void start() {
        System.out.println(VIEW_NAME);
    }

    @Override
    public void result(final DrawResult res) {
        System.out.println(res.toString());
    }
}

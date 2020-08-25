package com.xxAMIDOxx.xxSTACKSxx.ui.fixtures;

import com.xxAMIDOxx.xxSTACKSxx.ui.models.Menu;
import com.xxAMIDOxx.xxSTACKSxx.ui.models.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class Hooks {

    private final static Logger LOGGER = LoggerFactory.getLogger(Hooks.class);


    public static void deleteAllMenusFromPreviousRun() {
        Requests.getMenusBySearchTerm("(Automated Test Data)");
        ResponseWrapper responseWrapper = lastResponse().body().as(ResponseWrapper.class);
        List<Menu> listOfMenusToDelete = responseWrapper.getResults();

        for (Menu currentMenu : listOfMenusToDelete) {
            Requests.deleteTheMenu(currentMenu.getId());
            restAssuredThat(response -> response.statusCode(200));

            LOGGER.info(String.format("The menu with '%s' id was successfully deleted.", currentMenu.getId()));
        }
    }
}

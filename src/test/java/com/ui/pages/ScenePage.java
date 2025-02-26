package com.ui.pages;

import com.microsoft.playwright.Page;
import com.ui.fragments.*;
import com.ui.fragments.panelmenu.PanelMenu;
import com.ui.fragments.rightmenu.RightMenu;
import lombok.Getter;

@Getter
public class ScenePage extends BasePage{

    private final RightMenu rightmenu;
    private final PanelMenu panelMenu;
    private final ContextPanel contextPanel;
    private final ContextMenu contextMenu;
    private final TableView tableView;
    private final ViewPanel viewPanel;
    private final LeftToolMenu leftToolMenu;

    public ScenePage(Page page) {
        super(page);
        rightmenu = new RightMenu(page);
        panelMenu = new PanelMenu(page);
        contextPanel = new ContextPanel(page);
        contextMenu = new ContextMenu(page);
        tableView = new TableView(page);
        viewPanel = new ViewPanel(page);
        leftToolMenu = new LeftToolMenu(page);
    }
}

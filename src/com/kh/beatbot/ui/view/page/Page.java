package com.kh.beatbot.ui.view.page;

import com.kh.beatbot.ui.view.TouchableView;

public abstract class Page extends TouchableView {
	public static MainPage mainPage;
	
	public abstract void update();
}

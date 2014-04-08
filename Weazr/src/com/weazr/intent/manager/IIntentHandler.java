package com.weazr.intent.manager;

import android.content.Intent;

public interface IIntentHandler {

	public static final String DRAWABLE_RT = "drawable";
	public static final String R_PACKAGE = "com.weazr.main";
	
	public void handle(Intent intent);
}

package com.douzone.web.mysite.mvc.guestbook;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;
import com.douzone.web.mysite.mvc.main.MainAction;

public class GuestActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("del".equals(actionName)) {
			
			action = new delAction();
		}
		else if("list".equals(actionName)){
			
			action = new ListAction();
		}
		else if("add".equals(actionName)){
			
			action = new AddAction();
		}
		else if("delData".equals(actionName)){
			
			action = new DelDataAction();
		}
		else {
			
			action = new MainAction();
		}
		
		return action;
	}

}

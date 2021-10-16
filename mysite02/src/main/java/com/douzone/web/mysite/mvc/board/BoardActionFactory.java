package com.douzone.web.mysite.mvc.board;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;
import com.douzone.web.mysite.mvc.main.MainAction;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		
		Action action = null;
		if("list".equals(actionName)) {
			
			action = new ListAction();
		}
		else if("write".equals(actionName)) {
			
			action = new WriteFormAction();
		}
		else if("view".equals(actionName)) {
			
			action = new ViewAction();
		}
		else if("add".equals(actionName)) {
			
			action = new BoardAddAction();
		}
		else if("modify".equals(actionName)) {
			
			action = new ModifyAction();
		}
		else if("modifySuccess".equals(actionName)) {
			
			action = new ModifySuccessAction();
		}
		else if("reply".equals(actionName)) {
			
			action = new ReplyAction();
		}
		else if("replySuccess".equals(actionName)) {
			
			action = new ReplySuccessAction();
		}		
		else {
			
			action = new MainAction();
		}
		
		return action;
	}

}

package code.pattern.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import code.Patttern.MessageState;
import code.domain.MessageT;
import code.service.MessageServiceI;

public class Amessage {
	private MessageState messagestate;
	public void setAmessage( MessageState messagestate){
		this.messagestate =messagestate;
	}
	
	public  List<MessageT> findMessage(Set<MessageT> accMessage, List<MessageT> Message){
		return messagestate.state(accMessage, Message);
		
	}
}



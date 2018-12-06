package code.pattern.impl;

import java.util.List;
import java.util.Set;

import code.Patttern.MessageState;
import code.domain.MessageT;

public class Messagenoread extends MessageState{
	public List<MessageT> state(Set<MessageT> accMessage,List<MessageT> noreadMessage){
		for(MessageT message:accMessage)
		{
			if(message.getIsRead()==0)
			{
				noreadMessage.add(message);
			}
		}
		return noreadMessage;
	}
	
}
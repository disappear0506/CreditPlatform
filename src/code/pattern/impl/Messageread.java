package code.pattern.impl;

import java.util.List;
import java.util.Set;

import code.Patttern.MessageState;
import code.domain.MessageT;

public class Messageread extends MessageState{
	public List<MessageT> state(Set<MessageT> accMessage,List<MessageT> readMessage){
		for(MessageT message:accMessage)
		{
			if(message.getIsRead()==1)
			{
				readMessage.add(message);
			}
		}
		return readMessage;
	}
	
}

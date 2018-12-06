package code.Patttern;

import java.util.List;
import java.util.Set;

import code.domain.MessageT;

public abstract class MessageState {
    public abstract List<MessageT> state(Set<MessageT> accMessage,List<MessageT> noreadMessage);
}

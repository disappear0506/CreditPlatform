package code.service;

import code.domain.MessageT;

public interface MessageServiceI {
	void save(MessageT message);
	void update(MessageT message);
}

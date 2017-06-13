/*
 * P2PClient.h
 *
 *  Created on: Apr 27, 2017
 *      Author: user
 */

#ifndef P2PCLIENT_H_
#define P2PCLIENT_H_

#include "TCPSocket.h"
#include "MThread.h"
#include <pthread.h>
#include <iostream>

namespace networkingLab {

class P2PClient : public MThread {
private:
	TCPSocket* clientSocket;
	bool runningFlag;
public:
	P2PClient();
	virtual ~P2PClient();
	bool open (string peerIp, int port);
	void send (string msg);
	void close ();
	void run();
};

} /* namespace networkingLab */

#endif /* P2PCLIENT_H_ */

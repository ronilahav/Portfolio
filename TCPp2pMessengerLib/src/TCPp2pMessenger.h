/*
 * TCPp2pMessenger.h
 *
 *  Created on: Apr 27, 2017
 *      Author: user
 */

#ifndef TCPP2PMESSENGER_H_
#define TCPP2PMESSENGER_H_

#include <iostream>
#include "MThread.h"
#include "P2PServer.h"
#include "P2PClient.h"
#include "View.h"

namespace networkingLab {

class TCPp2pMessenger : public MThread, View {
private:
	P2PClient* client;
	P2PServer* server;
	int serverPort;
	bool isActiveClient;
public:
	TCPp2pMessenger(int serverPort);
	virtual ~TCPp2pMessenger();
	bool open (string peerIp, int port);
	void send (string msg);
	void reply (string msg);
	void close ();
	bool isActiveClientSession ();
	bool isActivePeerSession ();
	void run();
	void handleMsg(char* msg);
};

} /* namespace networkingLab */

#endif /* TCPP2PMESSENGER_H_ */

/*
 * P2PServer.h
 *
 *  Created on: Apr 27, 2017
 *      Author: user
 */

#ifndef P2PSERVER_H_
#define P2PSERVER_H_

#include "View.h"
#include "TCPSocket.h"
#include "MThread.h"
#include <pthread.h>
#include <iostream>

namespace networkingLab {

class P2PServer : public MThread {
private:
	TCPSocket* serverSocket;
	TCPSocket* connectSocket;
	bool runningFlag;
	View* handler;
public:
	P2PServer(View* view);
	virtual ~P2PServer();
	void init (int port);
	void reply (string msg);
	void close ();
	void run();
	bool isConnect();
};

} /* namespace networkingLab */

#endif /* P2PSERVER_H_ */

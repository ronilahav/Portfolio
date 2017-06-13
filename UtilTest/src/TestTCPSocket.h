/*
 * TestTCPSocket.h
 *
 *  Created on: Jun 12, 2017
 *      Author: user
 */

#ifndef TESTTCPSOCKET_H_
#define TESTTCPSOCKET_H_

#include "TCPSocket.h"
#include "MThread.h"
#include <pthread.h>
#include <iostream>

namespace networkingLab {

class TestTCPSocket : public MThread {
private:
	TCPSocket* server;
	TCPSocket* connect_socket;
	TCPSocket* client;
	bool stop;

public:
	TestTCPSocket();
	virtual ~TestTCPSocket();
	bool test();
	void run();
};

} /* namespace networkingLab */

#endif /* TESTTCPSOCKET_H_ */

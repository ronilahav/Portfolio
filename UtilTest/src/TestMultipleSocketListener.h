/*
 * TestMultipleSocketListener.h
 *
 *  Created on: Jun 12, 2017
 *      Author: user
 */

#ifndef TESTMULTIPLESOCKETLISTENER_H_
#define TESTMULTIPLESOCKETLISTENER_H_

#include <iostream>
#include <string.h>
#include "TCPSocket.h"
#include "MThread.h"
#include <unistd.h>
#include "MultipleSocketListener.h"

#define TEST_SIZE 20	// the number of open sockets to test simultaneously

using namespace std;
namespace networkingLab {

class TestMultipleSocketListener {
public:
	TestMultipleSocketListener();
	virtual ~TestMultipleSocketListener();
	bool test();
	void createClientAndSendRecvMsg(int port);
};

} /* namespace networkingLab */

#endif /* TESTMULTIPLESOCKETLISTENER_H_ */

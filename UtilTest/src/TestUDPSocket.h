/*
 * TestUDPSocket.h
 *
 *  Created on: Jun 12, 2017
 *      Author: user
 */

#ifndef TESTUDPSOCKET_H_
#define TESTUDPSOCKET_H_

#include "UDPSocket.h"

namespace networkingLab {

class TestUDPSocket {
public:
	TestUDPSocket();
	virtual ~TestUDPSocket();
	bool test();
};

} /* namespace networkingLab */

#endif /* TESTUDPSOCKET_H_ */

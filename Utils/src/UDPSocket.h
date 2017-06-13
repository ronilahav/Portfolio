/*
 * UDPSocket.h
 *
 *  Created on: Apr 24, 2017
 *      Author: user
 */

#ifndef SRC_UDPSOCKET_H_
#define SRC_UDPSOCKET_H_

#include "Socket.h"

using namespace std;
namespace networkingLab {

class UDPSocket : public Socket {
public:
	virtual ~UDPSocket();
	UDPSocket(int port = 9999);
	int read(char* buffer, int size);
	int sendTo(string msg, string peerIp, int port);
	int write(const char* msg, int len);
	int reply(string msg);
};
} /* namespace networkingLab */
#endif /* SRC_UDPSOCKET_H_ */

/*
 * TCPSocket.h
 *
 *  Created on: Apr 26, 2017
 *      Author: user
 */

#ifndef SRC_TCPSOCKET_H_
#define SRC_TCPSOCKET_H_

#include "Socket.h"

using namespace std;
namespace networkingLab {

class TCPSocket : public Socket {
public:
	TCPSocket(int sokc, sockaddr_in local_addr);
	TCPSocket(int port);
	TCPSocket(string peerIp, int port);
	virtual ~TCPSocket();
	TCPSocket* listenAndAccept();
	int read(char* buffer, int size);
	int write(const char* msg, int len);

private:
	void setsockopt();
};
} /* namespace networkingLab */
#endif /* SRC_TCPSOCKET_H_ */

/*
 * Socket.h
 *
 *  Created on: May 29, 2017
 *      Author: user
 */

#ifndef SRC_SOCKET_H_
#define SRC_SOCKET_H_

#include <strings.h>
#include <stdlib.h>
#include <iostream>
#include <stdio.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <sys/socket.h>
#include "FileInterface.h"

using namespace std;
namespace networkingLab{

class Socket  : public FileInterface {
protected:
	int socket_fd;
	struct sockaddr_in local_addr, remote_addr;
public:
	Socket(int type);
	Socket();
	virtual ~Socket();
	int bind(int port);
	int connect(string peerIp, int port);
	void close();
	int getSock();
	string getLocalIp();
	int getLocalPort();
	string getPeerIp();
	int getPeerPort();
};
} /* namespace networkingLab */
#endif /* SRC_SOCKET_H_ */

/*
 * UDPSocket.cpp
 *
 *  Created on: Apr 24, 2017
 *      Author: user
 */

#include "UDPSocket.h"

namespace networkingLab {

UDPSocket::~UDPSocket() {}
UDPSocket::UDPSocket(int port) : Socket(SOCK_DGRAM) {
	if (socket_fd < 0) {
		perror("Error opening channel");
		close();
		exit(1);
	}
	if(bind(port) <0)
	{
		close();
		exit(2);
	}
}

int UDPSocket::read(char* buffer, int size) {
	unsigned int fsize = sizeof(remote_addr);
	return recvfrom(socket_fd, buffer, size, 0, (struct sockaddr *) &remote_addr, &fsize);
}

int UDPSocket::sendTo(string msg, string peerIp, int port) {
	connect(peerIp, port);
	return write(msg.data(), msg.length());
}
int UDPSocket::write(const char* msg, int len){
	return ::write(socket_fd, msg, len);
}

int UDPSocket::reply(string msg) {
	connect(getPeerIp(), getPeerPort());
	return write(msg.data(), msg.length());
}
} /* namespace networkingLab */

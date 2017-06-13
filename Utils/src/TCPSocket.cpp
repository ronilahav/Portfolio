/*
 * TCPSocket.cpp
 *
 *  Created on: Apr 26, 2017
 *      Author: user
 */

#include "TCPSocket.h"

namespace networkingLab {

TCPSocket::~TCPSocket() {}
TCPSocket::TCPSocket(int sock, sockaddr_in remote_addr) {
	this->socket_fd = sock;
	this->remote_addr = remote_addr;
}

TCPSocket::TCPSocket(int port) : Socket(SOCK_STREAM){
	setsockopt();
	if(bind(port) <0)
	{
		close();
		exit(2);
	}
}

TCPSocket::TCPSocket(string peerIp, int port) : Socket(SOCK_STREAM){
	setsockopt();
	if(connect(peerIp, port) < 0)
	{
		close();
		exit(3);
	}
}

TCPSocket* TCPSocket::listenAndAccept() {
	listen(socket_fd, 1);
	unsigned int len = sizeof(remote_addr);
	int connect_sock = accept(socket_fd, (struct sockaddr *)&remote_addr, &len);
	if (connect_sock > 0)
		return new TCPSocket(connect_sock, remote_addr);
	else
		return NULL;
}

int TCPSocket::read(char* buffer, int size) {
	return ::read(socket_fd, buffer, size);
}

int TCPSocket::write(const char* msg, int len){
	return ::write(socket_fd, msg, len);
}

void TCPSocket::setsockopt() {
	int optval = 1;
			::setsockopt(socket_fd, SOL_SOCKET, SO_REUSEADDR, &optval, sizeof optval);
		if (socket_fd < 0) {
			perror("Error opening channel");
			close();
			exit(1);
		}
}
} /* namespace networkingLab */

//============================================================================
// Name        : main.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include "TCPp2pMessenger.h"

#define SERVER_PORT 2000;

using namespace std;
using namespace networkingLab;

void printInstructions(){
	cout<<"To open a connection to a peer type o <ip> <port>"<<endl;
	cout<<"To send a message type: s <message>"<<endl;
	cout<<"To reply to a message type: r <message>"<<endl;
	cout<<"To exit type: x"<<endl;
}

int main() {
	int port = SERVER_PORT;
	TCPp2pMessenger* messenger = new TCPp2pMessenger(port);
	string msg;
	string command;

	cout<<"Welcome to TCPp2p messenger"<<endl;
	printInstructions();
	while(true){
		cin >> command;
		if(command == "o")
		{
			string peerIp;
			cin >> peerIp;
			int peerPort;
			cin >> peerPort;
			messenger->open(peerIp, peerPort);
		}
		else if(command == "s")
		{
			getline(cin,msg);
			if (messenger->isActiveClientSession())
			{
				if(msg.size()>0 && msg[0] == ' ') msg.erase(0,1);
				messenger->send(msg);
			}
			else
				cout<< "No peer is connected" <<endl;
		}
		else if (command == "r"){
			getline(cin,msg);
			if (messenger->isActivePeerSession())
			{
				if(msg.size()>0 && msg[0] == ' ') msg.erase(0,1);
				messenger->reply(msg);
			}
			else
				cout<< "No peer is connected" <<endl;
		}
		else if (command == "x")
			break;
		else
		{
			cout<<"wrong input"<<endl;
			printInstructions();
		}
	}

	messenger->close();
	delete messenger;
	cout<<"messenger was closed"<<endl;
	return 0;
}


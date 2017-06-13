//============================================================================
// Name        : UtilTest.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include "TestMThreadAndGuard.h"
#include "TestUDPSocket.h"
#include "TestTCPSocket.h"
#include "TestMultipleSocketListener.h"
#include "TestFile.h"

using namespace std;
using namespace networkingLab;

int main() {
	cout << "Testing..." << endl;
	bool res = false;
	string sTest;

	/*	Test MThreadAndGuard
	TestMThreadAndGuard* test1 = new TestMThreadAndGuard();
	res = test1->test();
	sTest = "TestMThreadAndGuard";
		if (res == true)
		cout<< sTest <<" PASS" <<endl;
	else
		cout << sTest <<" FAIL!!!" <<endl;
	delete test1;
	//*/

	//*	Test TestUDPSocket
		TestUDPSocket* test2 = new TestUDPSocket();
	res = test2->test();
	sTest = "Test UDPSocket";
	if (res == true)
		cout<< sTest <<" PASS" <<endl;
	else
		cout << sTest <<" FAIL!!!" <<endl;
	delete test2;
	//*/

	//*	Test TCPSocket
	TestTCPSocket* test3 = new TestTCPSocket();
	res = test3->test();

	sTest = "Test TCPSocket";
	if (res == true)
		cout<< sTest <<" PASS" <<endl;
	else
		cout << sTest <<" FAIL!!!" <<endl;
	delete test3;
	//*/

	//* Test MultipleSocketListener
	TestMultipleSocketListener* test4 = new TestMultipleSocketListener();
	res = test4->test();
	sTest = "Test MultipleSocketListener";
	if (res == true)
		cout<< sTest <<" PASS" <<endl;
	else
		cout << sTest <<" FAIL!!!" <<endl;
	delete test4;
	//*/

	//* Test File
	TestFile* test5 = new TestFile();
	res = test5->test();
	sTest = "Test File";
	if (res == true)
		cout<< sTest <<" PASS" <<endl;
	else
		cout << sTest <<" FAIL!!!" <<endl;
	delete test5;
	//*/

	return 0;
}

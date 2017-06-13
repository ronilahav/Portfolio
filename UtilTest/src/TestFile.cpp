/*
 * TestFile.cpp
 *
 *  Created on: Jun 12, 2017
 *      Author: user
 */

#include "TestFile.h"

namespace networkingLab {

TestFile::TestFile() {}

TestFile::~TestFile() {}

bool TestFile::test() {
	bool flag = false;
	string path = "file.txt";
	string s = "hello\n";
	char buffer[256];
	bzero(buffer,256);
	//create and open file
	File* f1 = new File(path);
	//write to file
	f1->write(s.data(), s.length());
	//close file
	f1->close();
	//open file
	f1 = new File(path);
	//read from file
	f1->read(buffer, s.length());
	//cout<<buffer<<endl;
	//close file
	f1->close();
	if (strcasecmp(s.data(), buffer) == 0)
		flag = true;
	else
		flag = false;
	return flag;
}

} /* namespace networkingLab */

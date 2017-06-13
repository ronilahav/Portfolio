/*
 * File.h
 *
 *  Created on: May 30, 2017
 *      Author: user
 */

#ifndef SRC_FILE_H_
#define SRC_FILE_H_

#include "FileInterface.h"
#include <iostream>
#include <stdio.h>

using namespace std;
namespace networkingLab {

class File : public FileInterface {
private:
	FILE* file;
public:
	File(string path);
	virtual ~File();
	void close();
	int read(char* buffer, int size);
	int write(const char* msg, int len);
};
} /* namespace networkingLab */
#endif /* SRC_FILE_H_ */

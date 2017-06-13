/*
 * TestFile.h
 *
 *  Created on: Jun 12, 2017
 *      Author: user
 */

#ifndef TESTFILE_H_
#define TESTFILE_H_

#include "File.h"
#include <iostream>
#include <strings.h>

using namespace std;
namespace networkingLab {

class TestFile {
public:
	TestFile();
	virtual ~TestFile();
	bool test();
};

} /* namespace networkingLab */

#endif /* TESTFILE_H_ */

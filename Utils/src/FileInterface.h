/*
 * FileInterface.h
 *
 *  Created on: May 30, 2017
 *      Author: user
 */

#ifndef SRC_FILEINTERFACE_H_
#define SRC_FILEINTERFACE_H_

namespace networkingLab {

class FileInterface {
public:
	virtual ~FileInterface(){}
	virtual void close()=0;
	virtual int write(const char* msg, int len)=0;
	virtual int read(char* buffer, int size)=0;
};
} /* namespace networkingLab */
#endif /* SRC_FILEINTERFACE_H_ */

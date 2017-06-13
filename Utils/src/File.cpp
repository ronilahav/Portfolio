/*
 * File.cpp
 *
 *  Created on: May 30, 2017
 *      Author: user
 */

#include "File.h"

namespace networkingLab {

File::File(string path) {
	file = fopen(path.data(),"a+");
}

File::~File() {
	delete file;
}

void File::close() {
	fclose(file);
}

int File::read(char* buffer, int size) {
	return fread(buffer, sizeof(char), size, file);
}

int File::write(const char* msg, int len) {
	return fwrite(msg, sizeof(char), len, file);
}
} /* namespace networkingLab */
